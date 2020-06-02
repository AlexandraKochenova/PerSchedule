package com.example.client.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FamilyUserRelation {
    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("userFirst")
    @Expose
    private User userFirst;

    @SerializedName("userSecond")
    @Expose
    private User userSecond;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(User userFirstID) {
        this.userFirst = userFirstID;
    }

    public User getUserSecond() {
        return userSecond;
    }

    public void setUserSecond(User userSecondID) {
        this.userSecond = userSecondID;
    }
}
