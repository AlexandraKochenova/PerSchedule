package com.example.client.responses;

import com.example.client.classes.Pet;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPetsResponse extends BaseResponse {

    @SerializedName("petsList")
    @Expose
    public List<Pet> petsList;

    public List<Pet> getPetsList() {
        return petsList;
    }

    public void setPetsList(List<Pet> petsList) {
        this.petsList = petsList;
    }
}
