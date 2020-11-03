package com.forfresh.service;

import java.net.URI;
import java.net.URISyntaxException;

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
import com.forfresh.model.dto.kakaopay.KakaoPayApprovalVO;
import com.forfresh.model.dto.kakaopay.KakaoPayReadyVO;
import com.forfresh.model.dto.kakaopay.PaymentList;
import com.forfresh.model.dto.kakaopay.TotalPayment;

import lombok.extern.java.Log;
 
@Service
@Log
public class KakaoPayService {
 
    private static final String HOST = "https://kapi.kakao.com";
    
    @Autowired
    PaymentListDao paymentListDao;

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private String payiedUserId;
    
    public String kakaoPayReady(@RequestBody TotalPayment totalpay) {
 
        RestTemplate restTemplate = new RestTemplate();
        payiedUserId=totalpay.getUserId();
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
        params.add("approval_url", "http://localhost:8080/api/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/api/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/api/kakaoPaySuccessFail");
 
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
    
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
    	 
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
            PaymentList payList = new PaymentList(kakaoPayApprovalVO.getTid(),payiedUserId,Integer.parseInt(kakaoPayApprovalVO.getItem_code()),kakaoPayApprovalVO.getQuantity(),kakaoPayApprovalVO.getAmount().getTotal());
            paymentListDao.save(payList);
            return kakaoPayApprovalVO;
        
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
 