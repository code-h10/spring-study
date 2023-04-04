package com.binary.session.controller;

import com.binary.session.dto.LoginForm;
import com.binary.session.model.User;
import com.binary.session.service.UserService;
import com.spring.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionLoginController {

    private final UserService userService;

    @PostMapping("/login/v1")
    public Response<User> loginV1(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = userService.getByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if (isNull(user)) {
            return Response.success(400, false, "Not Found User", null);
        }

        session.setAttribute("JSESSIONID", user);
        return Response.success(200, true, "success", user);
    }

    @GetMapping("/login/v2")
    public Response<User> loginV2(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        User user = userService.getByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if (isNull(user)) {
            return Response.success(400, false, "Not Found User", null);
        }

        session.setAttribute("JSESSIONID", user);
        return Response.success(200, true, "success", user);
    }

    @GetMapping("/logout")
    public Response<String> logoutV1(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        session.invalidate();
        return Response.success(200, true, "success",null);
    }


    @GetMapping("/info")
    public Response<String> getSession(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("JSESSIONID");

        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        log.info("isNew={}", session.isNew());
        log.info("userInfo={}", user);

        return Response.success(200, true, "success",null);
    }
}
