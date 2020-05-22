package com.example.client.server_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerPet {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("age")
    @Expose
    public long age; //переводить в date

    @SerializedName("sex")
    @Expose
    public Boolean sex;

    @SerializedName("weight")
    @Expose
    public double weight;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("responsibilityList")
    @Expose
    public String responsibilityList;

    @SerializedName("ownerFamilyId")
    @Expose
    public int ownerFamilyId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getResponsibilityList() {
        return responsibilityList;
    }

    public void setResponsibilityList(String responsibilityList) {
        this.responsibilityList = responsibilityList;
    }

    public int getOwnerFamilyId() {
        return ownerFamilyId;
    }

    public void setOwnerFamilyId(int ownerFamilyId) {
        this.ownerFamilyId = ownerFamilyId;
    }

}
