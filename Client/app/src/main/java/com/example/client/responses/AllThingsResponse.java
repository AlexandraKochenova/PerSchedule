package com.example.client.responses;

import com.example.client.classes.ServerThing;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllThingsResponse extends BaseResponse {

    @SerializedName("thingsList")
    @Expose
    private List<ServerThing> ThingsList;

    public List<ServerThing> getThingsList() {
        return ThingsList;
    }

    public void setThingsList(List<ServerThing> thingsList) {
        ThingsList = thingsList;
    }
}
