package com.example.client.classes.thingsclasses;

import com.example.client.classes.Thing;

public class Product extends Thing {

    private int calorificValue;


    public Product(String name) {
        this.name = name;
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(String name, int calorificValue, double store) {
        this.name = name;
        this.calorificValue = calorificValue;
        this.store = store;
    }

    public Product(int id, String name, int calorificValue, double store){
        this.id = id;
        this.name = name;
        this.calorificValue = calorificValue;
        this.store = store;
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

    public int getCalorificValue() {
        return calorificValue;
    }

    public void setCalorificValue(int calorificValue) {
        this.calorificValue = calorificValue;
    }

    public double getStore() {
        return store;
    }

    public void pickFromStore(double x) {
        this.store -= x;
    }

    public void setStore(double store) {
        this.store = store;
    }

    @Override
    public String getInformation() {
        return this.store + "@" + this.calorificValue;
    }

    @Override
    public void setInformation(String value){
        this.store = Double.valueOf(value.split("@")[0]);
        this.calorificValue = Integer.valueOf(value.split("@")[1]);
    }
}
