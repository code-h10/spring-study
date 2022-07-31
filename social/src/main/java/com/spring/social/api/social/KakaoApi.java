package com.spring.social.api.social;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface KakaoApi {

    @GET("/oauth/token")
    Call<Map> getAccessToken(@QueryMap Map<String, String> params);

    @POST("/v2/user/me")
    Call<Map> getUserInfo(@Header("Authorization") String authorization);
}
