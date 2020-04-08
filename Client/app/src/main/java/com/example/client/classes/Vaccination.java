package com.example.client.classes;

import java.util.Date;

public class Vaccination extends Responsibility {

    private String type;

    public Vaccination(int petId, int id, String name, Date responsibilityDate, int period, String type) {
        super(petId, id, name, responsibilityDate, period);
        this.type = type;
    }
    @Override
    public int getType() {
        return 4;
    }

}
