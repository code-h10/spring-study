package com.binary.session.service;

import com.binary.session.dto.LoginForm;
import com.binary.session.model.User;
import com.binary.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByEmailAndPassword(String email, String password) {
        return userRepository.getByEmailAndPassword(email, password);
    }
}
