package com.binary.session.repository;

import com.binary.session.dto.LoginForm;
import com.binary.session.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    User getByEmailAndPassword(String email, String password);
}
