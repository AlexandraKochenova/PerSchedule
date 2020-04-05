package com.example.client.classes;

public class Feeding extends Responsibility{

    private Feed _feed;

    public Feeding(Feed feed){
        _feed = feed;
        //подумать, стоит ли сюда проставлять текущую дату или закидывать при заполнении какую то особенную
    }

    public Feed get_feed() {
        return _feed;
    }

    public void set_feed(Feed _feed) {
        this._feed = _feed;
    }
}
