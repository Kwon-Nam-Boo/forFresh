package com.forfresh.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.forfresh.model.dao.kakaopay.PaymentListDao;
import com.forfresh.model.dao.product.ProductDao;
import com.forfresh.model.dao.product.ShoppingListDao;
import com.forfresh.model.dao.refrig.FoodlistDao;
import com.forfresh.model.dto.kakaopay.KakaoPayApprovalVO;
import com.forfresh.model.dto.kakaopay.KakaoPayReadyVO;
import com.forfresh.model.dto.kakaopay.PaymentList;
import com.forfresh.model.dto.kakaopay.TotalPayment;
import com.forfresh.model.dto.product.Product;
import com.forfresh.model.dto.refrig.Foodlist;

import lombok.extern.java.Log;
 
@Service
@Log
public class KakaoPayService {
 
    private static final String HOST = "https://kapi.kakao.com";
    
    @Autowired
    PaymentListDao paymentListDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    ShoppingListDao shoppingListDao;
    @Autowired
    FoodlistDao foodlistDao;
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private String payiedUserId;
    private String shopList;
    private String stockList;
    private String productList;
    private String refrigNo;
    private String priceList;
    
    public String kakaoPayReady(@RequestBody TotalPayment totalpay) {
 
        RestTemplate restTemplate = new RestTemplate();
        payiedUserId=totalpay.getUserId();
        shopList = totalpay.getShoplistNo();
        stockList=totalpay.getStockList();  
        productList = totalpay.getProductNo();
        ///// 임시로 구현한 foodlist 저장 ///////////
        refrigNo = totalpay.getRefrigNo();
        priceList = totalpay.getPriceList();
        
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "179048868b71ef28f95e2b938400973b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "forfresh");
        params.add("item_name", totalpay.getItemName());
        params.add("item_code", totalpay.getProductNo());
        params.add("quantity", totalpay.getQuantity());
        params.add("total_amount", totalpay.getTotalAmount());
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://k3a407.p.ssafy.io/api/kakaoPaySuccess");
        params.add("cancel_url", "http://k3a407.p.ssafy.io/api/kakaoPayCancel");
        params.add("fail_url", "http://k3a407.p.ssafy.io/api/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            log.info("" + kakaoPayReadyVO);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
        
    }
    
    public String kakaoPayInfo(String pg_token) {
    	 
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "179048868b71ef28f95e2b938400973b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "forfresh");
        params.add("pg_token", pg_token);
//        params.add("total_amount", "1000");
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("****************************" + kakaoPayApprovalVO);
            LocalDateTime date = LocalDateTime.now();
            PaymentList payList = new PaymentList(kakaoPayApprovalVO.getTid(),payiedUserId,kakaoPayApprovalVO.getItem_code(),kakaoPayApprovalVO.getQuantity(),kakaoPayApprovalVO.getAmount().getTotal(),date);
            //결제 내역 저장
            paymentListDao.save(payList);
            //상품 수량 감소
            String[] tempProductList = productList.split(" ");
            String[] tempStockList = stockList.split(" ");
            for (int i = 0; i < tempProductList.length; i++) {
				Optional<Product> tmpProduct = productDao.findById(Integer.parseInt(tempProductList[i]));
				tmpProduct.get().setStock(tmpProduct.get().getStock()-Integer.parseInt(tempStockList[i]));
				productDao.save(tmpProduct.get());
            }
            //장바구니 삭제
            if(!shopList.equals("no")) {
            	String tempShopList[] = shopList.split(" ");
            	for (int i = 0; i < tempShopList.length; i++) {
            		shoppingListDao.deleteById(Integer.parseInt(tempShopList[i]));
            	}
            }
            //refrig 냉장고 식품저장
            // 냉장고번호가없다면(냉장고에 않넣을거라면) 무시(foodlist에 안넣어도된다)
        if(!refrigNo.equals("no")){
        	
            tempProductList = productList.split(" ");
            tempStockList = stockList.split(" ");
            String[] tempPriceList = priceList.split(" ");
            for (int i = 0; i < tempProductList.length; i++) {
            	Foodlist foodlist = new Foodlist();
    			Optional<Product> tmpProduct = productDao.findById(Integer.parseInt(tempProductList[i]));
    			foodlist.setRefrigNo(Integer.parseInt(refrigNo));
    			foodlist.setFoodName(tmpProduct.get().getDescription());
    			int categoryNo = tmpProduct.get().getCategoryNo();
    			foodlist.setCategoryNo(categoryNo);
    			// 냉동이면 냉동, 나머지 냉장
    			if(categoryNo == 10 || categoryNo == 11) {
    				foodlist.setStatus("냉동");
    			}else {
    				foodlist.setStatus("냉장");
    			}
    			foodlist.setStock(Integer.parseInt(tempStockList[i]));
    			foodlist.setPrice(Integer.parseInt(tempPriceList[i]));
    			foodlistDao.save(foodlist);
            }
        }
        /////
        // System.out.println("삐삐");
            return "redirect:http://k3a407.p.ssafy.io/paymentsuccess";
        
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
}
 