package com.example.client.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.client.classes.FamilyUserRelation;
import com.example.client.classes.Thing;
import com.example.client.classes.responsibilitiesclasses.Feeding;
import com.example.client.classes.responsibilitiesclasses.Medicine;
import com.example.client.classes.Pet;
import com.example.client.classes.thingsclasses.Pill;
import com.example.client.classes.thingsclasses.Product;
import com.example.client.classes.Responsibility;
import com.example.client.classes.User;
import com.example.client.classes.responsibilitiesclasses.Walk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pets.db";
    private static final int SCHEMA = 1;
    public static final String PETS_TABLE = "pets_list";
    public static final String PRODUCT_TABLE = "product_table";
    public static final String USER_TABLE = "user_table";
    public static final String RESPONSIBILITY_TABLE = "responsibility_table";
    public static final String RESPONSIBILITY_READY_TABLE = "responsibility_ready_table";
    public static final String MEDICINE_TABLE = "medicine_table";
    public static final String FRIENDSHIP_TABLE = "friendship_table";

    public static final String COLUMN_PETS_ID = "pets_id";
    public static final String COLUMN_PETS_ID_SERVER = "pets_id_server";
    public static final String COLUMN_PETS_NAME = "pets_name";
    public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_SERVER_ID = "product_server_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CALORIC = "caloric";
    public static final String COLUMN_PRODUCT_STORE = "product_store";

    public static final String COLUMN_MEDICINE_NAME = "medicine_name";
    public static final String COLUMN_MEDICINE_SERVER_ID = "medicine_server_id";
    public static final String COLUMN_MEDICINE_ID = "medicine_id";
    public static final String COLUMN_MEDICINE_STORE = "medicine_store";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_SERVER_ID = "user_server_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_LAST_NAME = "user_last_name";
    public static final String COLUMN_USER_LOGIN = "login";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_FAMILY_ID = "family_id";
    public static final String COLUMN_USER_IS_CURRENT = "is_current_user";

    public static final String COLUMN_RESPONSIBILITY_ID = "responsibility_id";
    public static final String COLUMN_RESPONSIBILITY_SERVER_ID = "responsibility_server_id";
    public static final String COLUMN_RESPONSIBILITY_NAME = "responsibility_name";
    public static final String COLUMN_RESPONSIBILITY_PERIOD = "responsibility_period";
    public static final String COLUMN_RESPONSIBILITY_PETS_ID = "pets_id";
    public static final String COLUMN_RESPONSIBILITY_USER_ID = "user_id";
    public static final String COLUMN_RESPONSIBILITY_INFORMATION = "responsibility_information";
    public static final String COLUMN_RESPONSIBILITY_TIME = "responsibility_time";

    public static final String COLUMN_RESPONSIBILITY_READY_ID = "responsibility_ready_id";
    public static final String COLUMN_RESPONSIBILITY_READY_DATE = "responsibility_ready_date";

    public static final String COLUMN_FRIENDSHIP_ID = "friendship_id";
    public static final String COLUMN_FRIENDSHIP_SERVER_ID = "friendship_server_id";
    public static final String COLUMN_FRIENDSHIP_USER_FIRST = "friendship_user_first";
    public static final String COLUMN_FRIENDSHIP_USER_SECOND = "friendship_user_second";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PETS_TABLE + " (" +
                COLUMN_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PETS_ID_SERVER + " INTEGER," +
                COLUMN_PETS_NAME + " TEXT," +
                COLUMN_DATE_OF_BIRTH + " TEXT," +
                COLUMN_SEX + " INTEGER," +
                COLUMN_TYPE + " TEXT"+ ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + PRODUCT_TABLE + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_SERVER_ID + " TEXT," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_CALORIC + " INTEGER," +
                COLUMN_PRODUCT_STORE + " REAL" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_SERVER_ID + " INTEGER," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_LOGIN + " TEXT," +
                COLUMN_USER_PASSWORD + " TEXT," +
                COLUMN_USER_FAMILY_ID + " TEXT," +
                COLUMN_USER_IS_CURRENT + " TEXT," +
                COLUMN_USER_LAST_NAME + " TEXT" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + RESPONSIBILITY_TABLE + "(" +
                COLUMN_RESPONSIBILITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RESPONSIBILITY_SERVER_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_NAME + " TEXT," +
                COLUMN_RESPONSIBILITY_PERIOD + " INTEGER," +
                COLUMN_RESPONSIBILITY_TIME + " TEXT," +
                COLUMN_RESPONSIBILITY_INFORMATION + " TEXT," +
                COLUMN_RESPONSIBILITY_PETS_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_USER_ID + " INTEGER" +");");
        sqLiteDatabase.execSQL("CREATE TABLE " + RESPONSIBILITY_READY_TABLE + "( " +
                COLUMN_RESPONSIBILITY_READY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RESPONSIBILITY_ID + " INTEGER," +
                COLUMN_RESPONSIBILITY_READY_DATE + " TEXT" + ")");
        sqLiteDatabase.execSQL("CREATE TABLE " + MEDICINE_TABLE + "(" +
                COLUMN_MEDICINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MEDICINE_SERVER_ID + " INTEGER,"+
                COLUMN_MEDICINE_NAME + " TEXT," +
                COLUMN_MEDICINE_STORE + " REAL" +")");
        sqLiteDatabase.execSQL("CREATE TABLE " + FRIENDSHIP_TABLE + "(" +
                COLUMN_FRIENDSHIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FRIENDSHIP_SERVER_ID + " INTEGER," +
                COLUMN_FRIENDSHIP_USER_FIRST + " TEXT," +
                COLUMN_FRIENDSHIP_USER_SECOND + " TEXT"+ ")");
        sqLiteDatabase.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PETS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RESPONSIBILITY_READY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RESPONSIBILITY_TABLE);
        onCreate(sqLiteDatabase);
    }

    /////PETS

    public List<Pet> selectPets(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Pet> list = new ArrayList<Pet>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.PETS_TABLE, null);
        if(query.moveToFirst()) {
            int indexName = query.getColumnIndex(DatabaseHelper.COLUMN_PETS_NAME);
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_PETS_ID_SERVER);
            int indexDate = query.getColumnIndex(DatabaseHelper.COLUMN_DATE_OF_BIRTH);
            int indexSex = query.getColumnIndex(DatabaseHelper.COLUMN_SEX);
            int indexType = query.getColumnIndex(DatabaseHelper.COLUMN_TYPE);
            do {

                int id = query.getInt(indexId);
                String name = query.getString(indexName);
                Date birth = new Date(Long.valueOf(query.getString(indexDate)));
                boolean sex = query.getInt(indexSex) == 1;
                String type = query.getString(indexType);
                Pet pet = new Pet(id, name, birth, sex, type);
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
        ContentValues cv = getCVFromPet(newPet);
        return sqLiteDatabase.insert(DatabaseHelper.PETS_TABLE, null, cv);
    }

    public long updatePet(Pet pet) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = getCVFromPet(pet);
        int x = sqLiteDatabase.update(DatabaseHelper.PETS_TABLE, cv, DatabaseHelper.COLUMN_PETS_ID_SERVER + " = ?", new String[] { String.valueOf(pet.getId())});
        if (x > 0){
            return x;
        }
        else {
            return saveNewPet(pet);
        }
    }

    /////RESPONSIBILITY

    public List<Responsibility> selectResponsibility(int petId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Responsibility> list = new ArrayList<Responsibility>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.RESPONSIBILITY_TABLE, null);
        if (query.moveToFirst()) {
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_ID);
            int indexName = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_NAME);
            int indexTime = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_TIME);
            int indexPetId = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_PETS_ID);
            int indexPeriod = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_PERIOD);
            int indexInformation = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_INFORMATION);
            do {
                int petID = query.getInt(indexPetId);
                if (petId == -1 ){

                }
                else{
                    if (petID != petId) {
                        continue;
                    }
                }


                int id = query.getInt(indexId);
                String name = query.getString(indexName);

                Date time = new Date(Long.valueOf(query.getString(indexTime)));
                if (doneResponsibilityForTime(id, time) > -1) {
                    time = new Date(doneResponsibilityForTime(id, time));
                }
                int period = query.getInt(indexPeriod);
                String[] information = query.getString(indexInformation).split("@");
                switch (name) {
                    case Constants.RESPONSIBILITY_CODE_FEDDING : {
                        List<Product> listProducts = selectProducts();
                        Product product = new Product(information[0]);
                        for (Product pro : listProducts) {
                            if (pro.getId() == Integer.valueOf(information[0])){
                                product = pro;
                            }
                        }

                        Feeding feeding = new Feeding(petID, id, name, time, period, product, Integer.valueOf(information[1]));
                        list.add(feeding);
                        break;
                    }
                    case Constants.RESPONSIBILITY_CODE_WALK : {
                        int timeForWalk = Integer.valueOf(information[0]);
                        Walk walk = new Walk(petID, id, name, time, period, timeForWalk);
                        list.add(walk);
                        break;
                    }
                    case Constants.RESPONSIBILITY_CODE_MEDICINE : {
                        String pill = information[0];
                        int value = Integer.valueOf(information[1]);
                        Medicine medicine = new Medicine(petID, id, name, time, period, pill, value);
                        list.add(medicine);
                        break;
                    }
                    case Constants.RESPONSIBILITY_CODE_VACCINATION : {

                    }
                }
            }while (query.moveToNext());
        }
        query.close();
        sqLiteDatabase.close();
        return list;
    }

    public long saveNewResponsibility(Responsibility newResponsibility) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_NAME, newResponsibility.getName());
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_PERIOD, newResponsibility.getPeriod());
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_TIME, newResponsibility.getResponsibilityDate().getTime());
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_PETS_ID, newResponsibility.getPetId());
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_INFORMATION, newResponsibility.getInformation());
        return sqLiteDatabase.insert(DatabaseHelper.RESPONSIBILITY_TABLE, null, cv);
    }

    public long doneResponsibilityForTime(int id, Date date) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<String> times = new ArrayList<>();

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.RESPONSIBILITY_READY_TABLE, null);
        if (query.moveToFirst()) {
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_ID);
            int indexDate = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_READY_DATE);
            do {
                int idResponsibility = query.getInt(indexId);
                if (idResponsibility == id) {
                    String[] x = query.getString(indexDate).split(";");
                    for (String str : x) {
                        times.add(str);
                    }
                }
            }while (query.moveToNext());
        }
        for(String time : times) {
            Date readyDate = new Date(Long.valueOf(time));
            long readyData = readyDate.getTime()/(1000 * 60 *60*24);
            long defaultDate = date.getTime()/(1000 * 60 * 60*24);
            long x = readyData - defaultDate;
            if (x == 0) {
                return readyDate.getTime();
            }
        }
        return -1;
    }

    public long readyResponsibility(int id, Date date) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.RESPONSIBILITY_READY_TABLE + " WHERE " + DatabaseHelper.COLUMN_RESPONSIBILITY_ID +" = " + id, null);
        String str;
        if (query.moveToFirst()) {
            int indexDate = query.getColumnIndex(DatabaseHelper.COLUMN_RESPONSIBILITY_READY_DATE);
            do {
                str = query.getString(indexDate);
            }while(query.moveToNext());
            str = str + ";" + String.valueOf(date.getTime());
        }
        else{
            str = String.valueOf(date.getTime());
        }
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_ID, id);
        cv.put(DatabaseHelper.COLUMN_RESPONSIBILITY_READY_DATE, str);
        if (doneResponsibilityForTime(id, date) < 0) {
            return sqLiteDatabase.insert(DatabaseHelper.RESPONSIBILITY_READY_TABLE, null, cv);
        }
        return sqLiteDatabase.update(DatabaseHelper.RESPONSIBILITY_READY_TABLE, cv,
                DatabaseHelper.COLUMN_RESPONSIBILITY_ID + " = ?", new String[] { String.valueOf(id) });
    }

    //THING
    public long saveNewThing(Thing thing) {
        if (thing.getInformation().split("@")[1].equals("-")) {
            return addNewPill((Pill)thing);
        }
        else{
            return saveNewProduct((Product) thing);
        }
    }


    /////PRODUCTS

    public long saveNewProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_PRODUCT_NAME, product.getName());
        cv.put(DatabaseHelper.COLUMN_PRODUCT_STORE, product.getStore());
        cv.put(DatabaseHelper.COLUMN_CALORIC, product.getCalorificValue());
        return sqLiteDatabase.insert(DatabaseHelper.PRODUCT_TABLE, null, cv);
    }

    public List<Product> selectProducts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Product> list = new ArrayList<Product>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.PRODUCT_TABLE, null);
        if (query.moveToFirst()) {
            int indexName = query.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_NAME);
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_ID);
            int indexStore = query.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_STORE);
            int indexCaloric = query.getColumnIndex(DatabaseHelper.COLUMN_CALORIC);
            do {
                int id = query.getInt(indexId);
                String name = query.getString(indexName);
                int caloric = query.getInt(indexCaloric);
                double store = query.getFloat(indexStore);
                Product product = new Product(id, name, caloric, store);
                list.add(product);
            }while(query.moveToNext());
        }
        return list;
    }

    public int pickStore(Product product) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_PRODUCT_STORE, product.getStore());
        return sqLiteDatabase.update(DatabaseHelper.PRODUCT_TABLE, cv, DatabaseHelper.COLUMN_PRODUCT_ID + " = ?", new String[] { String.valueOf(product.getId())});
    }

    /////MEDICINE

    public List<Pill> selectPill(){
        List<Pill> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.MEDICINE_TABLE, null);
        if (query.moveToFirst()){
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_MEDICINE_ID);
            int indexName = query.getColumnIndex(DatabaseHelper.COLUMN_MEDICINE_NAME);
            int indexStore = query.getColumnIndex(DatabaseHelper.COLUMN_MEDICINE_STORE);
            do {
                int id = query.getInt(indexId);
                String name = query.getString(indexName);
                double store = query.getFloat(indexStore);
                Pill pill = new Pill(id, name, store);
                list.add(pill);
            }while(query.moveToNext());
        }
        return list;
    }

    public long addNewPill(Pill pill){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_MEDICINE_NAME, pill.getName());
        cv.put(DatabaseHelper.COLUMN_MEDICINE_STORE, pill.getStore());
        return sqLiteDatabase.insert(DatabaseHelper.MEDICINE_TABLE, null, cv);
    }

    public int pickPillStore(Pill pill) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_MEDICINE_STORE, pill.getStore());
        return sqLiteDatabase.update(DatabaseHelper.MEDICINE_TABLE, cv, DatabaseHelper.COLUMN_MEDICINE_ID + " = ?", new String[] { String.valueOf(pill.getId())});
    }




    ////SERVER

    public long authUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_USER_SERVER_ID, user.getId());
        cv.put(DatabaseHelper.COLUMN_USER_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_USER_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_USER_FAMILY_ID, user.getFamilyId());
        cv.put(DatabaseHelper.COLUMN_USER_IS_CURRENT, "true");
        return sqLiteDatabase.insert(DatabaseHelper.USER_TABLE, null, cv);
    }

    public long saveNewUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = getCVFromUser(user);
        return sqLiteDatabase.insert(DatabaseHelper.USER_TABLE, null, cv);
    }

    public int getCurrentUserID(){
        int id = 0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE, null);
        if (query.moveToFirst()) {
            int indexID = query.getColumnIndex(DatabaseHelper.COLUMN_USER_SERVER_ID);
            int indexCurrentUser = query.getColumnIndex(DatabaseHelper.COLUMN_USER_IS_CURRENT);
            do {
                String isCurrentUser = query.getString(indexCurrentUser);
                if (isCurrentUser.equals("true")) {
                    id = query.getInt(indexID);
                    break;
                }
            }while(query.moveToNext());
        }
        return id;
    }

    public int getFamId() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE, null);
        if (query.moveToFirst()) {
            int indexFamId = query.getColumnIndex(DatabaseHelper.COLUMN_USER_FAMILY_ID);
            return Integer.valueOf(query.getString(indexFamId));
        }
        return 0;
    }

    public List<User> selectFamily(){
        List<User> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE, null);
        if (query.moveToFirst()){
            int indexId = query.getColumnIndex(DatabaseHelper.COLUMN_USER_SERVER_ID);
            int indexName = query.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
            int indexLastName = query.getColumnIndex(DatabaseHelper.COLUMN_USER_LAST_NAME);
            do {
                int id = query.getInt(indexId);
                String name = query.getString(indexName);
                String lastName = query.getString(indexLastName);
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                list.add(user);
            }while(query.moveToNext());
        }
        return list;
    }

    public long updateFam(User user){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = getCVFromUser(user);
        int x = sqLiteDatabase.update(DatabaseHelper.USER_TABLE, cv, DatabaseHelper.COLUMN_USER_SERVER_ID + " = ?", new String[] { String.valueOf(user.getId())});
        if (x > 0){
            return x;
        }
        else {
            return saveNewUser(user);
        }
    }


    //FRIENDSHIP

    public long addNewFriendship(FamilyUserRelation friendship) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_FRIENDSHIP_SERVER_ID, friendship.getID());
        try{
            JSONObject user1 = new JSONObject();
            user1.put(DatabaseHelper.COLUMN_USER_SERVER_ID, friendship.getUserFirst().getId());
            user1.put(DatabaseHelper.COLUMN_USER_NAME, friendship.getUserFirst().getName());
            user1.put(DatabaseHelper.COLUMN_USER_LAST_NAME, friendship.getUserFirst().getName());
            JSONObject user2 = new JSONObject();
            user2.put(DatabaseHelper.COLUMN_USER_SERVER_ID, friendship.getUserSecond().getId());
            user2.put(DatabaseHelper.COLUMN_USER_NAME, friendship.getUserSecond().getName());
            user2.put(DatabaseHelper.COLUMN_USER_LAST_NAME, friendship.getUserSecond().getName());
            cv.put(DatabaseHelper.COLUMN_FRIENDSHIP_USER_FIRST, user1.toString());
            cv.put(DatabaseHelper.COLUMN_FRIENDSHIP_USER_SECOND, user2.toString());
        }
        catch (Exception e) {

        }
        return sqLiteDatabase.insert(FRIENDSHIP_TABLE, null, cv);
    }

    public List<FamilyUserRelation> selectFriendships() {
        List<FamilyUserRelation> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.FRIENDSHIP_TABLE, null);
        if (query.moveToFirst()) {
            int indexID = query.getColumnIndex(DatabaseHelper.COLUMN_FRIENDSHIP_SERVER_ID);
            int indexUserFirst = query.getColumnIndex(DatabaseHelper.COLUMN_FRIENDSHIP_USER_FIRST);
            int indexUserSecond = query.getColumnIndex(DatabaseHelper.COLUMN_FRIENDSHIP_USER_SECOND);
            do {
                String userFirstJSON = query.getString(indexUserFirst);
                String userSecondJSON = query.getString(indexUserSecond);
                FamilyUserRelation familyUserRelation = new FamilyUserRelation();
                familyUserRelation.setID(query.getInt(indexID));
                try {
                    JSONObject object1 = new JSONObject(userFirstJSON);
                    User user1 = new User();
                    user1.setId(object1.getInt(DatabaseHelper.COLUMN_USER_SERVER_ID));
                    user1.setName(object1.getString(DatabaseHelper.COLUMN_USER_NAME));
                    user1.setLastName(object1.getString(DatabaseHelper.COLUMN_USER_LAST_NAME));
                    familyUserRelation.setUserFirst(user1);

                    JSONObject object2 = new JSONObject(userSecondJSON);
                    User user2 = new User();
                    user2.setId(object2.getInt(DatabaseHelper.COLUMN_USER_SERVER_ID));
                    user2.setName(object2.getString(DatabaseHelper.COLUMN_USER_NAME));
                    user2.setLastName(object2.getString(DatabaseHelper.COLUMN_USER_LAST_NAME));
                    familyUserRelation.setUserSecond(user2);

                }
                catch (Exception e) {

                }
                list.add(familyUserRelation);

            } while(query.moveToNext());
        }
        return list;
    }

    // GET CV

    private ContentValues getCVFromPet(Pet pet) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_PETS_ID_SERVER, pet.getId());
        cv.put(DatabaseHelper.COLUMN_PETS_NAME, pet.getName());
        cv.put(DatabaseHelper.COLUMN_DATE_OF_BIRTH, pet.getDateOfBirth().getTime());
        cv.put(DatabaseHelper.COLUMN_SEX, pet.getSex());
        cv.put(DatabaseHelper.COLUMN_TYPE, pet.getType());
        return cv;
    }

    private ContentValues getCVFromUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_USER_SERVER_ID, user.getId());
        cv.put(DatabaseHelper.COLUMN_USER_LOGIN, user.getLogin());
        cv.put(DatabaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
        cv.put(DatabaseHelper.COLUMN_USER_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_USER_LAST_NAME, user.getLastName());
        cv.put(DatabaseHelper.COLUMN_USER_FAMILY_ID, user.getFamilyId());
        cv.put(DatabaseHelper.COLUMN_USER_IS_CURRENT, "false");
        return cv;
    }

}
