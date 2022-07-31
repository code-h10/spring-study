package com.spring.social.controller;

import com.spring.common.dto.Response;
import com.spring.social.service.UserService;
import com.spring.social.utils.AppleProperties;
import com.spring.social.utils.FacebookProperties;
import com.spring.social.utils.GoogleProperties;
import com.spring.social.utils.KaKaoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    private FacebookProperties facebookProperties;
    private KaKaoProperties kaKaoProperties;
    private AppleProperties appleProperties;
    private GoogleProperties googleProperties;


    public UserController(GoogleProperties googleProperties, AppleProperties appleProperties, FacebookProperties facebookProperties, KaKaoProperties kaKaoProperties, UserService userService) {
        this.userService = userService;
        this.facebookProperties = facebookProperties;
        this.kaKaoProperties = kaKaoProperties;
        this.appleProperties = appleProperties;
        this.googleProperties = googleProperties;
    }

    @GetMapping("/password")
    public Response getUserPassword(@RequestParam String email) {
        return userService.findUserPassword(email);
    }


    @PostMapping("/password/token")
    public Response getResetPasswordToken(@RequestBody Map params) {
        return userService.findResetPasswordToken(params);
    }

    @PostMapping("/password/reset")
    public Response resetUserPassword(@RequestBody Map params) {
        return userService.resetUserPasswordByUserId(params);
    }


    @PostMapping("/save/email")
    public Response saveUserByEmail() {
        return Response.success(200, true, "", null);
    }

    @GetMapping("/login/google")
    public Response loginGoogle() {

        UriComponents loginUri = UriComponentsBuilder.newInstance()
                .fromUriString(googleProperties.getAccountHost())
                .queryParam("client_id", googleProperties.getClientId())
                .queryParam("redirect_uri", googleProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("status", googleProperties.getState())
                .queryParam("scope", googleProperties.getScope())
                .queryParam("access_type", googleProperties.getAccessType())
                .build();
        return Response.success(200, true, "", loginUri.toUri());
    }

    @GetMapping("/login/kakao")
    public Response loginKakao() {

        UriComponents loginUri = UriComponentsBuilder.newInstance()
                .fromUriString(kaKaoProperties.getOauthHost())
                .queryParam("client_id", kaKaoProperties.getClientId())
                .queryParam("redirect_uri", kaKaoProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("status", kaKaoProperties.getState())
                .build();
        return Response.success(200, true, "", loginUri.toUri());
    }


    @GetMapping("/login/facebook")
    public Response loginFacebook() {

        UriComponents loginUri = UriComponentsBuilder.newInstance()
                .fromUriString(facebookProperties.getOauthHost())
                .queryParam("client_id", facebookProperties.getClientId())
                .queryParam("redirect_uri", facebookProperties.getRedirectUri())
                .queryParam("status", facebookProperties.getState())
                .queryParam("scope", facebookProperties.getScope())
                .build();
        return Response.success(200, true, "", loginUri.toUri());
    }


    @GetMapping("/login/apple")
    public Response loginApple() {
        UriComponents loginUri = UriComponentsBuilder.newInstance()
                .fromUriString(appleProperties.getOauthHost())
                .queryParam("client_id", "125123")
                .queryParam("redirect_uri", "http://localhost:8080/api/v1/user/callback/apple")
                .queryParam("scope", "email name")
                .queryParam("response_type", "code id_token")
                .build();
        return Response.success(200, true, "", loginUri.toUri());
    }



    @GetMapping("/callback/facebook")
    public Response callBackByFacebook(HttpServletResponse response, String code) throws Exception {
        Map result = userService.callbackFacebook(facebookProperties, code);
        return Response.success(200, true, "", result);
    }

    @GetMapping("/callback/kakao")
    public Response callBackByKakao(String code, String state, String error, String error_description) throws Exception {
        Map result = userService.callbackKakao(kaKaoProperties,code);
        return Response.success(200, true, "", result);
    }

    @GetMapping("/callback/apple")
    public Response callBackByApple(Map params) throws Exception {
        Map result = userService.callbackApple(params);
        return Response.success(200, true, "", result);
    }


    @GetMapping("/callback/google")
    public Response callBackByGoogle(String code, String state) throws Exception {
        Map result = userService.callbackGoogle(googleProperties, code);
        return Response.success(200, true, "", result);
    }


}
