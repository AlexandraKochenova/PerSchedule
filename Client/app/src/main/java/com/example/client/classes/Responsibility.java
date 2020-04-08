package com.example.client.classes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Responsibility {

    protected int id;
    protected String name;
    protected int period;
    protected Date responsibilityDate;
    protected int petId;
    protected int userId;


    public Responsibility(int petId, int id, String name, Date responsibilityDate, int period){
        this.id = id;
        this.name = name;
        this.responsibilityDate = responsibilityDate;
        this.petId = petId;
        //this.userId = userId;
        this.period = period;
    }

    public Responsibility(int petId, String name, Date responsibilityDate, int period){
        this.name = name;
        this.responsibilityDate = responsibilityDate;
        this.petId = petId;
        //this.userId = userId;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public int getPetId() {
        return petId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public Date getResponsibilityDate() {
        return responsibilityDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsibilityTimeString(){
        SimpleDateFormat formatForTime = new SimpleDateFormat("HH:mm");
        return formatForTime.format(responsibilityDate);
    }

    public int getType(){
        return 0;
    }

    public String getInformation(){
        return "";
    }

    public String getInformationForActivity() {
        return "";
    }

}
