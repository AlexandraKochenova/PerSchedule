package com.example.client.responses;

import com.example.client.classes.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FamilyResponse extends BaseResponse {

    @SerializedName("familyList")
    @Expose
    public List<User> familyList;

    public List<User> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<User> familyList) {
        this.familyList = familyList;
    }
}
