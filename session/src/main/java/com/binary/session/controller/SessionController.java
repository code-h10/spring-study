package com.binary.session.controller;

import com.spring.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionController {


    @GetMapping("/login/v1")
    public Response<String> loginV1(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map user = new HashMap();
        user.put("id", "test@gmail.com");
        user.put("password", "code10");
        session.setAttribute("JSESSIONID", user);
        return Response.success(200, true, "success",null);
    }

    @GetMapping("/login/v2")
    public Response<String> loginV2(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Map user = new HashMap();
        user.put("id", "test@gmail.com");
        user.put("password", "code10");
        session.setAttribute("JSESSIONID", user);
        return Response.success(200, true, "success",null);
    }

    @GetMapping("/logout")
    public Response<String> logoutV1(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        session.invalidate();
        return Response.success(200, true, "success",null);
    }


    @GetMapping("/info")
    public Response getSession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Map user = (Map) session.getAttribute("JSESSIONID");

        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        log.info("isNew={}", session.isNew());
        log.info("userInfo={}", user);

        return Response.success(200, true, "success",null);
    }
}
