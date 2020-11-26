package com.forfresh.model.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.user.User;

public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndPassword(String userId, String password);
    Optional<User> findByNickName(String nickName);
}