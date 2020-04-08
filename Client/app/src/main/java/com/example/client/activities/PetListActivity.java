package com.example.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapters.PetAdapter;
import com.example.client.classes.Pet;
import com.example.client.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PetListActivity extends AppCompatActivity {

    private RecyclerView petsList;
    private DatabaseHelper dbHelper;
    private PetAdapter petAdapter;
    private FloatingActionButton addNewPetBtn;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        petsList = (RecyclerView) findViewById(R.id.pets_list);
        layoutManager = new LinearLayoutManager(this);
        petsList.setLayoutManager(layoutManager);
        petAdapter = new PetAdapter(getApplicationContext(), initData());
        petsList.setAdapter(petAdapter);
        addNewPetBtn = (FloatingActionButton) findViewById(R.id.add_new_pet_btn);
        addNewPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPet();
            }
        });
    }

    private List<Pet> initData(){
        List<Pet> list = dbHelper.selectPets();
        return list;
    }

    private void addNewPet(){
        Intent intent = new Intent(getApplicationContext(), NewPetActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
