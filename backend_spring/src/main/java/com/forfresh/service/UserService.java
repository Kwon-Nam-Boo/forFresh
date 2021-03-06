package com.forfresh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forfresh.model.dto.user.User;
import com.forfresh.model.dao.user.UserDao;

@Service
public class UserService {

	@Autowired
    UserDao userDao;
	
	
	// Id, Password 확인
	public Optional<User> findByIdAndPassword(String userId, String password){
		Optional<User> userOpt = userDao.findByUserIdAndPassword(userId, password);
		return userOpt;
	}
	
	
	// 전체 회원 조회
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		userDao.findAll().forEach(e -> userList.add(e));
		return userList;
	}

	// 단일 회원 조회
	public Optional<User> findById(String userId) {
		Optional<User> user = userDao.findById(userId);
		return user;
	}

	// 회원 가입
	public User save(User user) {
		userDao.save(user);
		return user;
	}

	// 회원 수정
	public void updateById(User u) {
			userDao.save(u);
	}

	// 회원 삭제
	public void deleteById(String userId) {
		userDao.deleteById(userId);
	}
	
	//닉네임중복체크 
	public Optional<User> findByNickName(String nickName) {
		Optional<User> user = userDao.findByNickName(nickName);
		return user;
	} 
}
