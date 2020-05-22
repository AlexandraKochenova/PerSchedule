package com.example.client.server_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerUser {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("login")
    @Expose
    public String login;

    @SerializedName("password")
    @Expose
    public String password;

    @SerializedName("familyId")
    @Expose
    public int familyId;

    @SerializedName("familyName")
    @Expose
    public String familyName;

    @SerializedName("isHeadOfFamily")
    @Expose
    public boolean isHeadOfFamily;


    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public boolean isHeadOfFamily() {
        return isHeadOfFamily;
    }

    public void setHeadOfFamily(boolean headOfFamily) {
        isHeadOfFamily = headOfFamily;
    }
}
