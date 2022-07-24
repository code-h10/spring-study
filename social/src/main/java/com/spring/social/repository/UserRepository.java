package com.spring.social.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserRepository {

    Map getUserByEmail(String email);
    Map getUserById(String id);

    void insertResetPasswordToken(Map params);
    void updateUserPasswordById(Map params);

    Map getResetPasswordToken(String token);
}
