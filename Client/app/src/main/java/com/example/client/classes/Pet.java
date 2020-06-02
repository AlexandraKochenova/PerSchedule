package com.example.client.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pet {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    private long age; //переводить в date

    @SerializedName("sex")
    @Expose
    private boolean sex;

    @SerializedName("weight")
    @Expose
    private double weight;

    @SerializedName("type")
    @Expose
    private String type;//подумать над типом пола

    @SerializedName("familyID")
    @Expose
    private int familyID;

    public Pet(int id) {
        this.id = id;
    }

    public Pet(int id, String name ) {
        this.id = id;
        this.name = name;
    }

    public Pet(String name, Date dateOfBirth, boolean sex, String type){

        this.name = name;
        this.age = dateOfBirth.getTime();
        this.sex = sex;
        this.type = type;
    }

    public Pet(int id, String name, Date dateOfBirth, boolean sex, String type) {
        this.id = id;
        this.name = name;
        this.age = dateOfBirth.getTime();
        this.sex = sex;
        this.type = type;
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

    public Date getDateOfBirth() {
        return new Date(this.age);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.age = dateOfBirth.getTime();
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getFamilyId() {
        return familyID;
    }

    public void setFamilyId(int ownerFamilyId) {
        this.familyID = ownerFamilyId;
    }
}
