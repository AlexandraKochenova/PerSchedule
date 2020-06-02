package com.example.client.responses;

import com.example.client.classes.ServerResponsibility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllResponsibilitiesResponse extends BaseResponse {

    @SerializedName("responsibilitieList")
    @Expose
    private List<ServerResponsibility> serverResponsibilityList;

    public List<ServerResponsibility> getServerResponsibilityList() {
        return serverResponsibilityList;
    }

    public void setServerResponsibilityList(List<ServerResponsibility> serverResponsibilityList) {
        this.serverResponsibilityList = serverResponsibilityList;
    }
}
