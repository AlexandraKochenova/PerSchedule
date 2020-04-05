package com.example.client.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

    private RecyclerView _petsList;
    private DatabaseHelper _dbHelper;
    private PetAdapter _petAdapter;
    private FloatingActionButton _addNewPetBtn;
    private LinearLayoutManager _layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_list_activity);
        _dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        _petsList = (RecyclerView) findViewById(R.id.pets_list);
        _layoutManager = new LinearLayoutManager(this);
        _petsList.setLayoutManager(_layoutManager);
        _petAdapter = new PetAdapter(getApplicationContext(), initData());
        _petsList.setAdapter(_petAdapter);
        _addNewPetBtn = (FloatingActionButton) findViewById(R.id.add_new_pet_btn);
        _addNewPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPet();
            }
        });
    }

    private List<Pet> initData(){
        List<Pet> list = _dbHelper.selectPets();
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
