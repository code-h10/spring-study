package com.spring.social.controller;

import com.spring.common.dto.Response;
import com.spring.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/password")
    public Response findUserPassword(@RequestParam String email) {
        return userService.findUserPassword(email);
    }


    @PostMapping("/password/token")
    public Response validateResetPasswordToken(@RequestBody Map params) {
        return userService.validateResetPasswordToken(params);
    }

    @PostMapping("/password/reset")
    public Response resetUserPassword(@RequestBody Map params) {
        return userService.resetUserPasswordById(params);
    }

}
