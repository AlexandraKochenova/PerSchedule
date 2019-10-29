package com.example.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.adapters.PetAdapter;
import com.example.client.classes.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetListActivity extends AppCompatActivity {

    private ListView petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_list_activity);

        petsList = (ListView) findViewById(R.id.pets_list);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, initData());
        PetAdapter adapter = new PetAdapter(getApplicationContext(), initData());

        petsList.setAdapter(adapter);
    }

    private List<Pet> initData(){
        List<Pet> list = new ArrayList<Pet>();
        list.add(new Pet("Максик"));
        list.add(new Pet("Шурик"));
        return list;
    }

    public void petItemOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), PetProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pet_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_new_pet:
                addNewPet();
                return true;
            case R.id.schedule_btn:
                openSchedule();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void addNewPet(){
        Intent intent = new Intent(getApplicationContext(), NewPetActivity.class);
        startActivity(intent);
    }

    private void openSchedule(){
        Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
        startActivity(intent);
    }

}
