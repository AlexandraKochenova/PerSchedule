package com.example.client.classes.responsibilitiesclasses;

import com.example.client.classes.Responsibility;
import com.example.client.helpers.Constants;

import java.util.Date;

public class Walk extends Responsibility {

    private int timeForWalk;
    //присобачить gps
    public Walk(int petId,
                   int userId,
                   long dateCreating,
                   long timeDoing,
                   int period,
                int timeForWalk){
        super(petId, Constants.RESPONSIBILITY_CODE_WALK, dateCreating, timeDoing, period, userId);
        this.timeForWalk = timeForWalk;
    }
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
        return String.valueOf(this.timeForWalk) + "@-";
    }

    @Override
    public String getInformationForActivity() {
        return String.valueOf(this.timeForWalk) + " мин.";
    }
}
