package com.example.client.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.fragments.FeedingFragment;
import com.example.client.fragments.MedicineFragment;
import com.example.client.fragments.WalkFragment;
import com.example.client.helpers.Constants;

public class NewResponsibilityActivity extends AppCompatActivity {

    private int petId;

    private Spinner responsibilityType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_responsibility);

        petId = Integer.valueOf(getIntent().getStringExtra("petId"));
        responsibilityType = (Spinner) findViewById(R.id.responsibility_choice);
        if (savedInstanceState == null) {
            FeedingFragment fragment = new FeedingFragment();
            fragment.setPetId(petId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.new_responsibility, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.responsibility_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        responsibilityType.setAdapter(adapter);
        responsibilityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0 :{
                        FeedingFragment fragment = new FeedingFragment();
                        fragment.setPetId(petId);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.new_responsibility, fragment)
                                .commit();
                        break;
                    }
                    case 1 :{
                        WalkFragment fragment = new WalkFragment();
                        fragment.setPetId(petId);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.new_responsibility, fragment)
                                .commit();
                        break;
                    }
                    case 2 :{
                        MedicineFragment fragment = new MedicineFragment();
                        fragment.setPetId(petId);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.new_responsibility, fragment)
                                .commit();
                        break;
                    }
                    case 3 :{

                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
