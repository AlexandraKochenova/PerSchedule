package com.example.client.interfaces;

import com.example.client.classes.User;
import com.example.client.requests.AuthPost;
import com.example.client.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerApi {

    @POST("auth")
    public Call<UserResponse> authorizationUser(@Body AuthPost data);
}