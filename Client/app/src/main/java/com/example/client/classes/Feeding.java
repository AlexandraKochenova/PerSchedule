package com.example.client.classes;

import java.util.Date;

public class Feeding extends Responsibility{

    private Product product;
    private int weightProduct;

    public Feeding(int petId, int id, String name, Date responsibilityDate, int period, Product product, int weightProduct){
        super(petId, id,name,responsibilityDate, period);
        this.product = product;
        this.weightProduct = weightProduct;

    }

    public Feeding(int petId, String name, Date responsibilityDate, int period, Product product, int weightProduct){
        super(petId, name, responsibilityDate, period);
        this.product = product;
        this.weightProduct = weightProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(int weightProduct) {
        this.weightProduct = weightProduct;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getInformationForActivity() {
        return this.product.getName() + ": " + this.weightProduct + " гр.";
    }

    @Override
    public String getInformation() {
        return String.valueOf(product.getId()) + ":" + String.valueOf(weightProduct);
    }
}
