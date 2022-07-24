package com.spring.social.service;

import com.spring.common.dto.Response;
import com.spring.social.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired private UserRepository userRepository;

    public Response findUserPassword(String email) {

        Map users = userRepository.getUserByEmail(email);

        if (Objects.isNull(users)) {
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


    public Response validateResetPasswordToken(Map params) {

        Map resetToken = userRepository.getResetPasswordToken(params.get("token").toString());

        if (Objects.isNull(resetToken)) {
            log.error("Token Not Found");
            return Response.failure("User Not Found");
        }

        if (isResetTokenExpired(resetToken)) {
            log.error("Token Expiration");
            return Response.failure("Token Expiration");
        }
        return Response.success(200, true, "", resetToken);
    }

    public Response resetUserPasswordById(Map params) {

        Response response = validateResetPasswordToken(params);
        if (response.getCode() != 200) {
            return response;
        }

        userRepository.updateUserPasswordById(params);
        return Response.success(200, true, "", null);
    }


    private boolean isResetTokenExpired(Map resetToken) {
        return LocalDateTime.now().isAfter(LocalDateTime.parse(resetToken.get("token").toString()));
    }

}

