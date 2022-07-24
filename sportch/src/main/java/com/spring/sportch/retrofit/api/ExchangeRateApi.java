package com.spring.sportch.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ExchangeRateApi {

    @GET("users/{user}/repos")
    Call<List> listRepos(@Path("user") String user);
}
