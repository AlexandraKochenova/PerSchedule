package com.example.client.classes;

public class Feed {

    private Product _product;
    private int weightProduct;

    public Feed(Product product) {
        _product = product;
    }

    public Product get_product() {
        return _product;
    }

    public void set_product(Product _product) {
        this._product = _product;
    }

    public int getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(int weightProduct) {
        this.weightProduct = weightProduct;
    }
}
