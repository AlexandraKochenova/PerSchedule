package com.example.client.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Family {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("headID")
    @Expose
    private int headID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeadID() {
        return headID;
    }

    public void setHeadID(int headID) {
        this.headID = headID;
    }
}
