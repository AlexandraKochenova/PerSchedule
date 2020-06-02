package com.example.client.interfaces;

import com.example.client.classes.FamilyUserRelation;
import com.example.client.classes.Pet;
import com.example.client.classes.ServerResponsibility;
import com.example.client.classes.ServerThing;
import com.example.client.classes.User;
import com.example.client.requests.AuthPost;
import com.example.client.responses.AllFriendshipResponse;
import com.example.client.responses.AllPetsResponse;
import com.example.client.responses.AllResponsibilitiesResponse;
import com.example.client.responses.AllThingsResponse;
import com.example.client.responses.FamilyResponse;
import com.example.client.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("new_user")
    public Call<String> registrationUser (@Body User serverUser);

    @POST("auth")
    public Call<UserResponse> authorizationUser(@Body AuthPost data);

    @POST("edit_user")
    public Call<String> editUser(@Body User user);

    @POST("delete_user")
    public Call<String> deleteUser(@Body User user);

    //FAMILY

    @POST("add_new_friendship")
    public Call<FamilyUserRelation> addNewFriendship(@Body FamilyUserRelation familyUserRelation);

    @POST("delete_new_friendship")
    public Call<String> deleteNewFriendship(@Body String id); //отказ в заявке

    @GET("get_new_friendships")
    public Call<AllFriendshipResponse> getNewFriendships(@Body String id);

    @POST("add_new_family_member")
    public Call<String> addNewFamilyMember(@Body String id);

    @POST("delete_family_member")
    public Call<String> deleteFamilyMember(@Body FamilyUserRelation familyUserRelation);

    @GET("get_family_users?")
    public Call<FamilyResponse> getFamilyMembers(@Query("id") String id);

    //PETS

    @POST("new_pet")
    public Call<String> newPet(@Body Pet serverPet);

    @POST("edit_pet")
    public Call<String> editPet(@Body Pet pet);

    @POST("delete_pet")
    public Call<String> deletePet(@Body String id);

    @GET("get_all_pet?")
    public Call<AllPetsResponse> getAllPets(@Query("id") String id);


    //RESPONSIBILITIES

    @POST("new_responsibility")
    public Call<String> newResponsibilities(@Body ServerResponsibility responsibility);

    @POST("edit_responsibility")
    public Call<String> editResponsibilities(@Body ServerResponsibility responsibility);

    @POST("delete_responsibility")
    public Call<String> deleteResponsibility(@Body ServerResponsibility responsibility);

    @GET("get_all_responsibilities/{id}")
    public Call<AllResponsibilitiesResponse> getAllResponsibilities(@Path("id") String id);

    //THINGS

    @POST("new_thing")
    public Call<String> newThing(@Body ServerThing thing);

    @POST("delete_thing")
    public Call<String> deleteThing(@Body String id);

    @POST("edit_thing")
    public Call<String> editThing(@Body ServerThing thing);

    @GET("get_all_things/{id}")
    public Call<AllThingsResponse> getAllThings(@Path("id") String id);
}