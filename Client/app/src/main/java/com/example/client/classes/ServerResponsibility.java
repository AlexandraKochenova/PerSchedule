package com.example.client.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponsibility {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("dateCreating")
    @Expose
    private long dateCreating;

    @SerializedName("timeDoing")
    @Expose
    private long timeDoing;

    @SerializedName("readyDates")
    @Expose
    private String readyDate;

    @SerializedName("period")
    @Expose
    private String period;

    @SerializedName("information")
    @Expose
    private String information;

    @SerializedName("responsibilityCode")
    @Expose
    private int responsibilityCode;

    @SerializedName("petId")
    @Expose
    private int petId;

    @SerializedName("userID")
    @Expose
    private int userID;

    public long getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(long dateCreating) {
        this.dateCreating = dateCreating;
    }

    public long getTimeDoing() {
        return timeDoing;
    }

    public void setTimeDoing(long timeDoing) {
        this.timeDoing = timeDoing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getResponsibilityCode() {
        return responsibilityCode;
    }

    public void setResponsibilityCode(int responsibilityCode) {
        this.responsibilityCode = responsibilityCode;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getReadyDate() {
        return readyDate;
    }

    public void setReadyDate(String readyDate) {
        this.readyDate = readyDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}

