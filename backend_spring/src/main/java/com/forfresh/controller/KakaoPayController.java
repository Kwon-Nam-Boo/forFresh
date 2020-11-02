package com.forfresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forfresh.model.dto.kakaopay.KakaoPayApprovalVO;
import com.forfresh.model.dto.kakaopay.TotalPayment;
import com.forfresh.service.KakaoPayService;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class KakaoPayController {
   
   @Setter(onMethod_ = @Autowired)
   private KakaoPayService kakaopay;
   
   
   @GetMapping("/kakaoPay")
   public void kakaoPayGet() {
       
   }
   
   @PostMapping("/kakaoPay")
   public String kakaoPay(@RequestBody TotalPayment totalpay) {
       log.info("kakaoPay post............................................");
       
       return "redirect:" + kakaopay.kakaoPayReady(totalpay);

   }
   
   @GetMapping("/kakaoPaySuccess")
   @ResponseBody
   public KakaoPayApprovalVO kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
       log.info("kakaoPaySuccess get............................................");
       log.info("kakaoPaySuccess pg_token : " + pg_token);
       return kakaopay.kakaoPayInfo(pg_token);
//       model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
   }
   
}