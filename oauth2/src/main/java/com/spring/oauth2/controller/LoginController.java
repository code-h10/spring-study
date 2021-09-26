package com.spring.oauth2.controller;



import com.spring.common.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("api/v1/oauth2")
public class LoginController {


    @GetMapping(value = "/login")
    public Response login() {



        return Response.success(200,  false, "테스트 라네");
    }
}
