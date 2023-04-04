package com.binary.session.controller;

import com.binary.session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cookie")
public class CookieLoginController {

    private final UserService userService;
    
}
