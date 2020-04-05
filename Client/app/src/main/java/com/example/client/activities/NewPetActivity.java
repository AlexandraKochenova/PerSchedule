package com.example.client.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.classes.Pet;
import com.example.client.helpers.DatabaseHelper;

import java.util.Date;
import java.util.GregorianCalendar;

public class NewPetActivity extends AppCompatActivity {

    private DatabaseHelper _dbHelper;

    private EditText _petName;
    private Pet _newPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pet_activity);

        _petName = (EditText) findViewById(R.id.pet_name_input);
    }

    @Override
    protected void onResume() {
        super.onResume();
        _dbHelper = new DatabaseHelper(getApplicationContext());

    }

    public void saveBtnOnClick(View view){
        _newPet = new Pet(_petName.getText().toString());
        //GregorianCalendar date = new GregorianCalendar(_petDateOfBirth.getYear(), _petDateOfBirth.getMonth()-1, _petDateOfBirth.getDayOfMonth());
        //_newPet.set_dateOfBirth(date);
        long result = _dbHelper.saveNewPet(_newPet);
        if (result <= 0) {
            Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
        }
        petListOpen();
    }

    public void petListOpen(){
        Intent intent = new Intent(getApplicationContext(), PetListActivity.class);
        startActivity(intent);
    }
}
