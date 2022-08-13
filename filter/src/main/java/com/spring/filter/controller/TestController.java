package com.spring.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {


    @GetMapping("/test")
    public String getTest(HttpServletRequest request, HttpServletResponse response, String test) {
        return test;
    }
}
