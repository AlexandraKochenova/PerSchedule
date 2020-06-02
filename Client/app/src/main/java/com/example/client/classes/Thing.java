package com.example.client.classes;

public class Thing {
    protected int id;
    protected String name;
    protected double store;

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

    public double getStore() {
        return store;
    }

    public void setStore(double store) {
        this.store = store;
    }

    public String getInformation(){
        return "";
    }

    public void setInformation(String value){
        this.store = Double.valueOf(value.split("@")[0]);
    }
}
