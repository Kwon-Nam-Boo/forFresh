package com.forfresh.controller;

import io.swagger.annotations.ApiResponses;

import java.util.Optional;
import java.util.Random;

import com.forfresh.model.dao.mail.MailDao;
import com.forfresh.model.dao.user.UserDao;
import com.forfresh.model.BasicResponse;
import com.forfresh.model.dto.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RestController
public class MailController {
    
    @Autowired
    UserDao userDao;
    @Autowired
    MailDao mailDao;
    
    @GetMapping("/mail/send")
    @ApiOperation(value = "이메일 발송")
    public Object mailConfirm(@RequestParam(required = true) String userId)  {
          
        Optional<User> emailChk = userDao.findById(userId);

        final BasicResponse result = new BasicResponse();

        if (emailChk.isPresent()) {
            result.status = false;
            result.data = "email overlapped";
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }  else {
            int ran = new Random().nextInt(900000) + 100000;
            String authCode = String.valueOf(ran);
            String subject = "회원가입 인증 코드 발급 안내 입니다.";
            StringBuilder sb = new StringBuilder();
            sb.append("귀하의 인증 코드는 " + authCode + "입니다.");
            mailDao.send(subject, sb.toString(), null, userId, null);
            result.status = true;
            result.data = Integer.toString(ran);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    
    

}