package com.example.client.classes;

public class Pill {
    private String name;
    private double store;
    private int id;

    public Pill(int id, String name, double store) {
        this.id = id;
        this.name = name;
        this.store = store;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void pickFromStore(double x) {
        this.store -= x;
    }
}
