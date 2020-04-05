package com.example.client.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.client.helpers.DatabaseHelper;

public class PetsDatabase {

    private SQLiteDatabase _db;
    private DatabaseHelper _dbHelper;
    private Cursor _query;


    public PetsDatabase (Context applicationContext) {
        _dbHelper = new DatabaseHelper(applicationContext);
    }
}
