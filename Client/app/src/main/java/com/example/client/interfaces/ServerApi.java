package com.example.client.interfaces;

import com.example.client.requests.AuthPost;
import com.example.client.responses.AllPetsResponse;
import com.example.client.responses.UserResponse;
import com.example.client.server_models.ServerPet;
import com.example.client.server_models.ServerUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("auth")
    public Call<UserResponse> authorizationUser(@Body AuthPost data);

    @POST("new_user")
    public Call<String> registrationUser(@Body ServerUser serverUser);

    @GET("get_all_pet?")
    public Call<AllPetsResponse> getAllPets(@Query("id") int id);

    @POST("new_pet")
    public Call<String> newPet(@Body ServerPet serverPet);
}