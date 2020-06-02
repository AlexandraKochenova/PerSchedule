package com.example.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.classes.Pet;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;
import com.example.client.helpers.NetworkService;
import com.example.client.helpers.ServerToClientHelper;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPetActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Pet newPet;
    private String type;

    private EditText petName;
    private CalendarView date;
    private RadioButton maleSex;
    private Spinner petTypeChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        petName = (EditText) findViewById(R.id.pet_name_input);
        date = (CalendarView) findViewById(R.id.pet_add_calendar_view);
        maleSex = (RadioButton) findViewById(R.id.radio_btn_sex_male);
        petTypeChoice = (Spinner) findViewById(R.id.pet_type_choice);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.type_pets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeChoice.setAdapter(adapter);
        petTypeChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choices = getResources().getStringArray(R.array.type_pets);
                type = choices[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    public void saveBtnOnClick(View view){
        String name = petName.getText().toString();
        Date date = new Date(this.date.getDate());
        boolean sex = maleSex.isChecked() ? true : false;

        String type = this.type;
        newPet = new Pet(name, date, sex, type);
        savePetToServer();
        long result = dbHelper.saveNewPet(newPet);
        if (result <= 0) {
            Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
        }
        petListOpen();
    }

    public void petListOpen(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void savePetToServer() {
        int ownerFamId = dbHelper.getFamId();
        newPet.setFamilyId(ownerFamId);
        NetworkService.getInstance()
                .getJSONApi()
                .newPet(newPet)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (Integer.valueOf(response.body()) >0) {
                            if (newPet != null) {
                                newPet.setId(Integer.valueOf(response.body()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed, Pet Not Created", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
