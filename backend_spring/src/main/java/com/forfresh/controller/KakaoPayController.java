package com.forfresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forfresh.model.BasicResponse;
import com.forfresh.model.dto.kakaopay.KakaoPayApprovalVO;
import com.forfresh.model.dto.kakaopay.TotalPayment;
import com.forfresh.service.KakaoPayService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Setter;
import lombok.extern.java.Log;
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin
@Log
@Controller
public class KakaoPayController {
   
   @Setter(onMethod_ = @Autowired)
   private KakaoPayService kakaopay;
   
   
   @GetMapping("/kakaoPay")
   public void kakaoPayGet() {
       
   }
   
   @PostMapping("/kakaoPay")
   @ResponseBody
   public String kakaoPay(@RequestBody TotalPayment totalpay) {
       log.info("kakaoPay post............................................");
       log.info("totalpay : "+totalpay.toString());
       return kakaopay.kakaoPayReady(totalpay);

   }
   
   @GetMapping("/kakaoPaySuccess")
   @ResponseBody
   public KakaoPayApprovalVO kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
       log.info("kakaoPaySuccess get............................................");
       log.info("kakaoPaySuccess pg_token : " + pg_token);
       return kakaopay.kakaoPayInfo(pg_token);
//       model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
   }
   
   @GetMapping("/kakaoPayCancel")
   @ResponseBody
   public String kakaoPayCancel() {
//	   log.info("kakaoPaySuccess get............................................");
//	   log.info("kakaoPaySuccess pg_token : ");
	   return "cancel";
//       model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
   }
   
   @GetMapping("/kakaoPaySuccessFail")
   @ResponseBody
   public String kakaoPaySuccessFail() {
//	   log.info("kakaoPaySuccess get............................................");
//	   log.info("kakaoPaySuccess pg_token : ");
	   return "success fail";
//       model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
   }
   
}