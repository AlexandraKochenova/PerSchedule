package com.example.client.server_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responsibility {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("dateTimeResponsibility")
    @Expose
    public long dateTimeResponsibility;

    @SerializedName("dateCreating")
    @Expose
    public long dateCreating;

    @SerializedName("timeDoing")
    @Expose
    public long timeDoing;

    @SerializedName("information")
    @Expose
    public String information;

    @SerializedName("responsibilityCode")
    @Expose
    public int responsibilityCode;

    @SerializedName("petId")
    @Expose
    public int petId;

    @SerializedName("ownerId")
    @Expose
    public int ownerId;

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

    public long getDateTimeResponsibility() {
        return dateTimeResponsibility;
    }

    public void setDateTimeResponsibility(long dateTimeResponsibility) {
        this.dateTimeResponsibility = dateTimeResponsibility;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
