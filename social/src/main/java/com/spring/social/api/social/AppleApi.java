package com.spring.social.api.social;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Map;

public interface AppleApi {

    @GET("/auth/keys")
    Call<Map> getPublicKey();
}
