package com.example.client.helpers;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Constants {
    public static final int ERROR = 1;
    public static final int OK = 0;

    public static final String ENTER_INT = "Please, enter int value.";
    public static final String SUCCESS = "Success.";

    public static final String FAILED = "Failed, Try again";

    public static final String WRONG_USER_DATA = "The username or password you entered is incorrect";

    public static final String USER_NOT_DELETED = "The user with this email has not been deleted.";
    public static final String USER_DELETED = "User successfully deleted.";
    public static final String USER_CANT_DELETED = "User can not been deleted, becouse already have one or more orders.";
    public static final String USER_NOT_FOUND = "User not found...";
    public static final String USER_ALREADY_EXISTS = "User with this email already exists.";
    public static final String USER_CREATED = "User created.";
    public static final String USER_ALREADY_DELETED = "User has already been deleted.";
    public static final String USER_RESTORED = "User successfully restored";
    public static final String USER_UPDATED = "User successfully updated";

    public static final String PET_CREATED = "Pet created.";
    public static final String PET_DELETED = "Pet successfully deleted.";
    public static final String PET_UPDATED = "Pet successfully updated.";
    public static final String PET_NOT_FOUND = "Pet not found.";

    public static final String RESPONSIBILITY_NOT_FOUND = "Responsibility not found";
    public static final String RESPONSIBILITY_CREATED = "Responsibility created.";
    public static final String RESPONSIBILITY_UPDATED = "Responsibility successfully updated.";
    public static final String RESPONSIBILITY_DELETED = "Responsibility successfully deleted.";

    public static final String RESPONSIBILITY_CODE_FEDDING = "Feeding";
    public static final String RESPONSIBILITY_CODE_WALK = "Walk";
    public static final String RESPONSIBILITY_CODE_MEDICINE = "Medicine";
    public static final String RESPONSIBILITY_CODE_VACCINATION = "Vaccination";

    public static final int NO_PERIOD = 0;
    public static final int EVERYDAY = 1;
    public static final int EVERYWEEK = 2;
    public static final int EVERYMONTH = 3;
    public static final int EVERYYEAR = 4;

    public static long currentDate(){
        Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.getInstance().YEAR),
                Calendar.getInstance().get(Calendar.getInstance().MONTH),
                Calendar.getInstance().get(Calendar.getInstance().DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }

}
