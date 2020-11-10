package com.forfresh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forfresh.model.BasicResponse;
import com.forfresh.model.dto.user.Idpass;
import com.forfresh.model.dto.user.User;
import com.forfresh.service.JwtService;
import com.forfresh.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RequestMapping("account")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestBody Idpass idpass, HttpServletResponse res) {
		Optional<User> userOpt = userService.findByIdAndPassword(idpass.getUserId(), idpass.getPassword());
		BasicResponse result = new BasicResponse();
		if (userOpt.isPresent()) {
			result.status = true;
			result.object = userOpt.get();
			// 성공하면 토큰 생성
			String token = jwtService.create(userOpt.get());
			res.setHeader("jwt-auth-token", token);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/user/join")
	@ApiOperation(value = "회원 가입")
	public Object save(@RequestBody User user) {
		BasicResponse result = new BasicResponse();
		Optional<User> userOpt = userService.findById(user.getUserId());

		// ID가 이미 존재하지 않을때만 회원가입 가능하다
		if (!userOpt.isPresent()) {
			result.status = true;
			userService.save(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/user/searchAll")
	@ApiOperation(value = "모든 회원 조회")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> user = userService.findAll();
		// BasicResponse result = new BasicResponse();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/search")
	@ApiOperation(value = "단일 회원 조회")
	public Object getUser(@RequestParam("userId") String userId) {
		System.out.println(userId);
		Optional<User> user = userService.findById(userId);
		BasicResponse result = new BasicResponse();
		if(user.isPresent()) {
			result.status = true;
			result.object = user.get();
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/search/nickname")
	@ApiOperation(value = "닉넴임 중복 확인용 조회")
	public Object checkNickName(@RequestParam("nickName") String nickName) {
		Optional<User> user = userService.findByNickName(nickName);
		BasicResponse result = new BasicResponse();
		if(user.isPresent()) {
			result.status = true;
			
			Map<String, String> userInfo = new HashMap<String, String>();
			userInfo.put("userId", user.get().getUserId());
			userInfo.put("nickName", user.get().getNickName());
			result.object = userInfo;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=true;
//			result.data="ok";
			return new ResponseEntity<>(result,  HttpStatus.OK);
		}
	}
	
	@PutMapping("/user/update/userinfo")
	@ApiOperation(value = "회원 수정 (바꿀 아이디 값과 , 바꿀 값 User 값)")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		Optional<User> findUser = userService.findById(user.getUserId());
		findUser.get().setAddr1(user.getAddr1());
		findUser.get().setAddr2(user.getAddr2());
		findUser.get().setAddr3(user.getAddr3());
		findUser.get().setNickName(user.getNickName());
		findUser.get().setPhone(user.getPhone());
		userService.updateById(findUser.get());
		
		return new ResponseEntity<User>(findUser.get(), HttpStatus.OK);
	}
	
	@PutMapping("/user/update/password")
	@ApiOperation(value = "회원 수정 (바꿀 아이디 값과 , 바꿀 값 User 값)")
	public ResponseEntity<User> updateUserPassword(@RequestBody User user) {
		Optional<User> findUser = userService.findById(user.getUserId());
		findUser.get().setPassword(user.getPassword());
		userService.updateById(findUser.get());
		return new ResponseEntity<User>(findUser.get(), HttpStatus.OK);
	}

	@DeleteMapping("/user/delete")
	@ApiOperation(value = "회원 삭제 ")
	public Object deleteMember(@RequestParam("userId") String userId) {
		BasicResponse result = new BasicResponse();
		try {
			result.status = true;
			userService.deleteById(userId);
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/token")
	@ApiOperation(value = "인터셉트로 유효성 확인 후 토큰 재갱신")
	public Object getInfo(HttpServletRequest req, @RequestParam("userId") String userId, HttpServletResponse res) {

		BasicResponse result = new BasicResponse();
		HttpStatus status = null;
		try {
			Optional<User> user = userService.findById(userId);
			String token = jwtService.create(user.get());

			// 토큰 정보는 request의 헤더로 보내고 나머지는 result에 담아주자
			res.setHeader("jwt-auth-token", token);
			result.status = true;
			result.object = user.get();
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			result.status = false;
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(result, status);
	}
}
