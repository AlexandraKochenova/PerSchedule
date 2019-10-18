package com.example.client.classes;

import java.util.ArrayList;
import java.util.Date;

public class Pet {

    private String _name;
    private Date _dateOfBirth;
    private ArrayList<Feeding> _feedingList;
    private Boolean _sex; //подумать над типом пола
    //тип животного (доп классовая система кошка собака попугай рыбка и тд)

    public Pet(String name) {
        _name = name;
        _feedingList = new ArrayList<>();
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Date get_dateOfBirth() {
        return _dateOfBirth;
    }

    public void set_dateOfBirth(Date _dateOfBirth) {
        this._dateOfBirth = _dateOfBirth;
    }

    public ArrayList<Feeding> get_feedingList() {
        return _feedingList;
    }

    public void set_feedingList(ArrayList<Feeding> _feedingList) {
        this._feedingList = _feedingList;
    }
}
