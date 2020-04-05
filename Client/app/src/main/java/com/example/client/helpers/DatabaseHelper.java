package com.example.client.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.client.classes.Pet;
import com.example.client.classes.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String PETS = "pets";
    public static final String PRODUCT = "product";

    private static final String DATABASE_NAME = "pets.db";
    private static final int SCHEMA = 1;
    public static final String PETS_TABLE = "pets_list";
    public static final String FEEDING_TABLE = "feeding_table";
    public static final String PRODUCT_TABLE = "product_table";
    public static final String USER_TABLE = "user_table";
    public static final String RESPONSIBILITY_TABLE = "responsibility_table";
    public static final String RESPONSIBILITY_READY_TABLE = "responsibility_ready_table";

    public static final String COLUMN_PETS_ID = "pets_id";
    public static final String COLUMN_PETS_ID_SERVER = "pets_id_server";
    public static final String COLUMN_PETS_NAME = "pets_name";
    public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_FEEDING_ID = "feeding_id";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_WEIGHT = "weight";

    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CALORIC = "caloric";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_LOGIN = "login";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_FAMILY_ID = "family_id";
    public static final String COLUMN_USER_IS_HEAD_OF_FAMILY = "is_head_of_family";

    public static final String COLUMN_RESPONSIBILITY_ID = "responsibility_id";
    public static final String COLUMN_RESPONSIBILITY_SERVER_ID = "responsibility_server_id";
    public static final String COLUMN_RESPONSIBILITY_NAME = "responsibility_name";
    public static final String COLUMN_RESPONSIBILITY_PERIOD = "responsibility_period";
    public static final String COLUMN_RESPONSIBILITY_PETS_ID = "pets_id";
    public static final String COLUMN_RESPONSIBILITY_USER_ID = "user_id";
    public static final String COLUMN_RESPONSIBILITY_INFORMATION = "responsibility_information";

    public static final String COLUMN_RESPONSIBILITY_READY_ID = "responsibility_ready_id";
    public static final String COLUMN_RESPONSIBILITY_READY_DATE = "responsibility_ready_date";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PETS_TABLE + " (" +
                COLUMN_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PETS_ID_SERVER + "INTEGER," +
                COLUMN_PETS_NAME + " TEXT," +
                COLUMN_DATE_OF_BIRTH + " TEXT," +
                COLUMN_SEX + " TEXT," +
                COLUMN_TYPE + " TEXT"+ ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + FEEDING_TABLE + " (" +
                COLUMN_FEEDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PETS_NAME + " TEXT," +
                COLUMN_TIME + " TEXT," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_WEIGHT + " TEXT" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + PRODUCT_TABLE + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_CALORIC + " INTEGER" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_LOGIN + " TEXT," +
                COLUMN_USER_PASSWORD + " TEXT," +
                COLUMN_USER_FAMILY_ID + " TEXT," +
                COLUMN_USER_IS_HEAD_OF_FAMILY + " TEXT" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + RESPONSIBILITY_TABLE + "(" +
                COLUMN_RESPONSIBILITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RESPONSIBILITY_SERVER_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_NAME + " TEXT," +
                COLUMN_RESPONSIBILITY_PERIOD + " INTEGER," +
                COLUMN_RESPONSIBILITY_INFORMATION + " TEXT," +
                COLUMN_RESPONSIBILITY_PETS_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_USER_ID + " INTEGER" +");");
        sqLiteDatabase.execSQL("CREATE TABLE " + RESPONSIBILITY_READY_TABLE + "( " +
                COLUMN_RESPONSIBILITY_READY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RESPONSIBILITY_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_READY_DATE + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Pet> selectPets(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Pet> list = new ArrayList<Pet>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.PETS_TABLE, null);
        if(query.moveToFirst()) {
            do {
                int id = query.getInt(0);
                String name = query.getString(1);
                Pet pet = new Pet(name);
                pet.setId(id);
                list.add(pet);
            }
            while (query.moveToNext());
        }
        query.close();
        sqLiteDatabase.close();
        return list;
    }

    public long saveNewPet(Pet newPet){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_PETS_NAME, newPet.get_name());
        //cv.put(DatabaseHelper.COLUMN_DATE_OF_BIRTH, newPet.get_dateOfBirth().toString());
        return sqLiteDatabase.insert(DatabaseHelper.PETS_TABLE, null, cv);
    }

    public long authUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_USER_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_USER_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_USER_FAMILY_ID, user.getFamilyId());
        cv.put(DatabaseHelper.COLUMN_USER_IS_HEAD_OF_FAMILY, user.isHeadOfFamily());
        return sqLiteDatabase.insert(DatabaseHelper.USER_TABLE, null, cv);
    }

}
