package com.example.client.responses;

import com.example.client.classes.FamilyUserRelation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllFriendshipResponse extends BaseResponse {

    @SerializedName("newFriendshipList")
    @Expose
    private List<FamilyUserRelation> newFriendshipList;

    public List<FamilyUserRelation> getNewFriendshipList() {
        return newFriendshipList;
    }

    public void setNewFriendshipList(List<FamilyUserRelation> newFriendshipList) {
        this.newFriendshipList = newFriendshipList;
    }
}
