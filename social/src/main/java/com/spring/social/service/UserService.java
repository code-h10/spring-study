package com.spring.social.service;

import com.spring.common.dto.Response;
import com.spring.social.api.social.FacebookApi;
import com.spring.social.api.social.GoogleApi;
import com.spring.social.api.social.KakaoApi;
import com.spring.social.repository.UserRepository;
import com.spring.social.utils.FacebookProperties;
import com.spring.social.utils.GoogleProperties;
import com.spring.social.utils.KaKaoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
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


    public Response resetUserPasswordByUserId(Map params) {

        Map resetToken = validateResetPasswordToken(params);
        if (resetToken.isEmpty()) {
            return Response.failure("Token Expiration");
        }
        userRepository.updateUserPasswordByUserId(params);
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

    private boolean isResetTokenExpired(Map resetToken) throws Exception {
        return LocalDateTime.now().isAfter(LocalDateTime.parse(resetToken.get("token").toString()));
    }

    public Map callbackFacebook(FacebookProperties facebookProperties, String code) throws IOException {
        log.debug("code :: " + code);

        if (code == null) {
            return null;
        }

        Map params = new HashMap();
        params.put("client_id", "541235814154575");
        params.put("redirect_uri", "http://localhost:8080/api/v1/user/callback/facebook");
        params.put("client_secret", "1eb6eb10b64c00b2b13f328b678f105d");
        params.put("code", code);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(facebookProperties.getGraphHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FacebookApi facebookApi = retrofit.create(FacebookApi.class);
        Call<Map> result = facebookApi.getAccessToken(params);
        retrofit2.Response<Map> ss = result.execute();

        Map body = ss.body();
        log.info("access token :: " + body);
        String accessToken = body.get("access_token").toString();

        Map userInfoParams = new HashMap();
        userInfoParams.put("access_token", accessToken);
        userInfoParams.put("fields","id,name,email,birthday,first_name,last_name");

        Call<Map> result2 = facebookApi.getUserInfo(userInfoParams);
        retrofit2.Response<Map> ss2 = result2.execute();

        Map facebookAccount = ss2.body();
        log.info("access token :: " + facebookAccount);
        return facebookAccount;
    }

    public Map callbackKakao(KaKaoProperties kaKaoProperties, String code) throws Exception {

        Map params = new HashMap();
        params.put("grant_type", "authorization_code");
        params.put("client_id", "cc324f7d4017583a32f029307769fe44");
        params.put("redirect_uri", "http://localhost:8080/api/v1/user/callback/kakao");
        params.put("client_secret", "9eEIYAc7s50n9EYnddzWcVVicQ2FZZQw");
        params.put("code", code);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kauth.kakao.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KakaoApi kakaoApi = retrofit.create(KakaoApi.class);
        Call<Map> result = kakaoApi.getAccessToken(params);
        retrofit2.Response<Map> ss = result.execute();

        Map body = ss.body();

        log.info("token :: " + body);

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(kaKaoProperties.getKApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KakaoApi kakaoApi2 = retrofit2.create(KakaoApi.class);
        String header = "Bearer " + body.get("access_token");

        Call<Map> result2 = kakaoApi2.getUserInfo(header);
        retrofit2.Response<Map> ss2 = result2.execute();

        Map body2 = ss2.body();
        Map kakaAccount = (Map) body2.get("kakao_account");

        Map user = userRepository.getUserByEmail(kakaAccount.get("email").toString());
        log.info("User Found", user);
        return kakaAccount;
    }

    public Map callbackApple(Map token) throws Exception {
        return Collections.emptyMap();
    }


    public Map callbackGoogle(GoogleProperties googleProperties, String code) throws Exception {

        if (code == null) {
            return null;
        }

        Map params = new HashMap();
        params.put("client_id", googleProperties.getClientId());
        params.put("redirect_uri", googleProperties.getRedirectUri());
        params.put("client_secret", googleProperties.getClientSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(googleProperties.getOauthHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GoogleApi googleApi = retrofit.create(GoogleApi.class);
        Call<Map> result = googleApi.getAccessToken(params);
        retrofit2.Response<Map> ss = result.execute();

        Map body = ss.body();

        log.info("token :: " + body);

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(googleProperties.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GoogleApi googleApi2 = retrofit2.create(GoogleApi.class);
        String token = body.get("access_token").toString();
        Call<Map> result2 = googleApi2.getUserInfo(token);
        retrofit2.Response<Map> ss2 = result2.execute();

        Map body2 = ss2.body();

        return body2;
    }

}

