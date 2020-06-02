package com.example.client.responses;

import com.example.client.classes.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends BaseResponse{

    @SerializedName("user")
    @Expose
    private User serverUser;

    public User getServerUser() {
        return serverUser;
    }

    public void setServerUser(User serverUser) {
        this.serverUser = serverUser;
    }
}
