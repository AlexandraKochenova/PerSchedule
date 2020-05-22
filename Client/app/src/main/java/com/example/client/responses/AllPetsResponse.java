package com.example.client.responses;

import com.example.client.server_models.ServerPet;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPetsResponse extends BaseResponse {

    @SerializedName("petsList")
    @Expose
    public List<ServerPet> petsList;

    public List<ServerPet> getPetsList() {
        return petsList;
    }

    public void setPetsList(List<ServerPet> petsList) {
        this.petsList = petsList;
    }
}
