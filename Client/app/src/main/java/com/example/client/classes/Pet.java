package com.example.client.classes;

import java.util.Date;

public class Pet {

    private int id;
    private String name;
    private Date dateOfBirth;
    private boolean sex;
    private String type;//подумать над типом пола

    public Pet(int id) {
        this.id = id;
    }

    public Pet(int id, String name ) {
        this.id = id;
        this.name = name;
    }

    public Pet(String name, Date dateOfBirth, boolean sex, String type){

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.type = type;
    }

    public Pet(int id, String name, Date dateOfBirth, boolean sex, String type) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
}
