package com.spring.social.api.social;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface FacebookApi {

    @GET("/v14.0/oauth/access_token")
    Call<Map> getAccessToken(@QueryMap Map<String, String> params);

    @GET("/me")
    Call<Map> getUserInfo(@QueryMap Map<String, String> params);

}
