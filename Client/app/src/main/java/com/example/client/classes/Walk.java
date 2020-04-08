package com.example.client.classes;

import java.util.Date;

public class Walk extends Responsibility {

    private int timeForWalk;
    //присобачить gps

    public Walk(int petId, int id, String name, Date responsibilityDate, int period, int timeForWalk) {
        super(petId, id, name, responsibilityDate, period);
        this.timeForWalk = timeForWalk;
    }
    public Walk(int petId, String name, Date responsibilityDate, int period, int timeForWalk) {
        super(petId, name, responsibilityDate, period);
        this.timeForWalk = timeForWalk;
    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public String getInformation() {
        return String.valueOf(this.timeForWalk) + ":-";
    }

    @Override
    public String getInformationForActivity() {
        return String.valueOf(this.timeForWalk) + " мин.";
    }
}
