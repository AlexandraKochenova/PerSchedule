package com.example.client.classes;

public class Product {

    private String _name;
    private int _calorificValue;

    public Product(String name) {
        _name = name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_calorificValue() {
        return _calorificValue;
    }

    public void set_calorificValue(int _calorificValue) {
        this._calorificValue = _calorificValue;
    }
}
