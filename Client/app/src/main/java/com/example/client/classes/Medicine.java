package com.example.client.classes;

import java.util.Date;

public class Medicine extends Responsibility {

    //private Pill pill;
    private String pill;
    private int value;

    public Medicine(int petId, int id, String name, Date responsibilityDate, int period, String pill, int value) {
        super(petId, id, name, responsibilityDate, period);
        this.pill = pill;
        this.value = value;
    }

    public Medicine(int petId, String name, Date responsibilityDate, int period, String pill, int value) {
        super(petId, name, responsibilityDate, period);
        this.pill = pill;
        this.value = value;
    }

    public String getPill() {
        return pill;
    }

    public void setPill(String pill) {
        this.pill = pill;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public String getInformation() {
//        return String.valueOf(pill.getId()) + ":" + String.valueOf(this.value);
        return pill + ":" + String.valueOf(this.value);
    }

    @Override
    public String getInformationForActivity() {
//        return String.valueOf(pill.getId()) + ": " + String.valueOf(this.value);
        return pill + ": " + String.valueOf(this.value);
    }
}
