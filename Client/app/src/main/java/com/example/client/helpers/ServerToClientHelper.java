package com.example.client.helpers;

import com.example.client.classes.ServerThing;
import com.example.client.classes.Thing;
import com.example.client.classes.responsibilitiesclasses.Feeding;
import com.example.client.classes.Responsibility;
import com.example.client.classes.ServerResponsibility;
import com.example.client.classes.responsibilitiesclasses.Medicine;
import com.example.client.classes.responsibilitiesclasses.Walk;
import com.example.client.classes.thingsclasses.Pill;
import com.example.client.classes.thingsclasses.Product;

import org.json.JSONObject;

public class ServerToClientHelper {

    public static Responsibility toClientResponsibility(ServerResponsibility serverResponsibility) {
        int period = Integer.valueOf(serverResponsibility.getPeriod());
        switch (serverResponsibility.getResponsibilityCode()) {
            case 0:
                Feeding feeding = new Feeding(serverResponsibility.getPetId(),
                        serverResponsibility.getUserID(),
                        serverResponsibility.getDateCreating(),
                        serverResponsibility.getTimeDoing(),
                        period);
                feeding.setId(serverResponsibility.getId());
                feeding.setReadyDate(serverResponsibility.getReadyDate());
                String[] product = serverResponsibility.getInformation().split("@");
                try {
                    JSONObject object = new JSONObject(product[0]);
                    Product product1 = new Product(object.getInt("idProduct"));
                    product1.setName(object.getString("nameProduct"));
                    product1.setInformation(object.getString("informationProduct"));
                    feeding.setProduct(product1);
                }
                catch (Exception e) {
                    feeding.setProduct(null);
                }
                feeding.setWeightProduct(Integer.valueOf(product[1]));
                return feeding;
            case 1:
                int timeForWalk = Integer.valueOf(serverResponsibility.getInformation().split("@")[0]);
                Walk walk = new Walk(serverResponsibility.getPetId(),
                        serverResponsibility.getUserID(),
                        serverResponsibility.getDateCreating(),
                        serverResponsibility.getTimeDoing(),
                        period,
                        timeForWalk);
                walk.setId(serverResponsibility.getId());
                walk.setReadyDate(serverResponsibility.getReadyDate());
                return walk;
            case 2:
                Medicine medicine = new Medicine(serverResponsibility.getPetId(),
                        serverResponsibility.getUserID(),
                        serverResponsibility.getDateCreating(),
                        serverResponsibility.getTimeDoing(),
                        period,
                        serverResponsibility.getInformation().split("@")[0],
                        Integer.valueOf(serverResponsibility.getInformation().split("@")[1]));
                medicine.setId(serverResponsibility.getId());
                medicine.setReadyDate(serverResponsibility.getReadyDate());
                return medicine;
            case 3:


                break;

        }
        return null;
    }

    public static ServerResponsibility toServerResponsibility(Responsibility responsibility) {
        ServerResponsibility output = new ServerResponsibility();
        output.setId(responsibility.getId());
        output.setDateCreating(responsibility.getDateCreating());
        output.setTimeDoing(responsibility.getTimeDoing());
        output.setReadyDate(responsibility.getReadyDate());
        output.setPeriod(String.valueOf(responsibility.getPeriod()));
        output.setPetId(responsibility.getPetId());
        output.setUserID(responsibility.getUserId());
        output.setInformation(responsibility.getInformation());
        switch (responsibility.getName()) {
            case Constants.RESPONSIBILITY_CODE_FEDDING:
                output.setResponsibilityCode(0);
                return output;
            case Constants.RESPONSIBILITY_CODE_WALK:
                output.setResponsibilityCode(1);
                return output;
            case Constants.RESPONSIBILITY_CODE_MEDICINE:
                output.setResponsibilityCode(2);
                return output;
            case Constants.RESPONSIBILITY_CODE_VACCINATION:
                output.setResponsibilityCode(3);
                return output;
        }
        return null;
    }

    public static Thing toClientThing(ServerThing serverThing) {
        switch (serverThing.getCode()) {
            case 0:
                Product product = new Product(serverThing.getId());
                product.setName(serverThing.getName());
                product.setInformation(serverThing.getInformation());
                return product;
            case 1:
                Pill pill = new Pill(serverThing.getId());
                pill.setName(serverThing.getName());
                pill.setInformation(serverThing.getInformation());
                return pill;
        }
        return null;
    }

    public static ServerThing toServerThing(Thing thing) {
        ServerThing serverThing = new ServerThing();
        serverThing.setId(thing.getId());
        serverThing.setName(thing.getName());
        serverThing.setInformation(thing.getInformation());
        if (thing.getInformation().split("@")[1].equals("-")){
            serverThing.setCode(1);
        }
        else{
            serverThing.setCode(0);
        }
        return serverThing;
    }

}
