package com.example.client.classes;

import java.util.Date;

public class Feeding {

    private Date _feedingTime;
    private Feed _feed;

    public Feeding(Feed feed){
        _feed = feed;
        //подумать, стоит ли сюда проставлять текущую дату или закидывать при заполнении какую то особенную
    }

    public Date get_feedingTime() {
        return _feedingTime;
    }

    public void set_feedingTime(Date _feedingTime) {
        this._feedingTime = _feedingTime;
    }

    public Feed get_feed() {
        return _feed;
    }

    public void set_feed(Feed _feed) {
        this._feed = _feed;
    }
}
