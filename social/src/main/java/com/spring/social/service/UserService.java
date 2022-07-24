package com.spring.social.service;

import com.spring.common.dto.Response;
import com.spring.social.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserService {

    @Autowired private UserRepository userRepository;

    public Response findUserPassword(String email) {

        Map users = userRepository.getUserByEmail(email);
        if (users == null) {
            log.error("User Not Found");
            return Response.failure("User Not Found");
        }

        String token = UUID.randomUUID().toString();
        Map resetToken = new HashMap();
        resetToken.put("user_id", users.get("id"));
        resetToken.put("token", token);
        userRepository.insertResetPasswordToken(resetToken);

        UriComponents link = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("utransfer.com")
            .path("reset/password")
            .build();

        sendResetPasswordEmail(users.get("email").toString(), link.toString(), "ko_KR");
        return Response.success(200, true, "Send Forgot Password Mail Success", null);
    }

    public void sendResetPasswordEmail(String email, String link, String locale) {

    }

    public Response findResetPasswordToken(Map params) {
        Map resetToken = validateResetPasswordToken(params);
        if (resetToken.isEmpty()) {
            return Response.failure("Token Expiration");
        }
        return Response.success(200, true, "", resetToken);
    }


    public Response resetUserPasswordById(Map params) {

        Map resetToken = validateResetPasswordToken(params);
        if (resetToken.isEmpty()) {
            return Response.failure("Token Expiration");
        }

        userRepository.updateUserPasswordById(params);
        return Response.success(200, true, "", null);
    }

    private Map validateResetPasswordToken(Map params)  {

        Map resetToken = userRepository.getResetPasswordToken(params.get("token").toString());
        if (resetToken == null) {
            log.error("Token Expiration");
            return Collections.emptyMap();
        }
        return resetToken;
    }

    private boolean isResetTokenExpired(Map resetToken) {
        return LocalDateTime.now().isAfter(LocalDateTime.parse(resetToken.get("token").toString()));
    }

}

