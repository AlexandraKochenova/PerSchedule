package com.example.client.classes;

import android.widget.Toast;

public class Product {

    private int id;
    private String name;
    private int calorificValue;
    private double store;

    public Product(String name) {
        this.name = name;
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
}
