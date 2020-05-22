package com.example.client.responses;

import com.example.client.server_models.ServerUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends BaseResponse{

    @SerializedName("user")
    @Expose
    private ServerUser serverUser;

    public ServerUser getServerUser() {
        return serverUser;
    }

    public void setServerUser(ServerUser serverUser) {
        this.serverUser = serverUser;
    }
}
