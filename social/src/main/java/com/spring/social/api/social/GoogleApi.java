package com.spring.social.api.social;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface GoogleApi {

    @POST("/token")
    Call<Map> getAccessToken(@QueryMap Map<String, String> params);

    @GET("/userinfo/v2/me")
    Call<Map> getUserInfo(@Query("access_token") String accessToken);
}
