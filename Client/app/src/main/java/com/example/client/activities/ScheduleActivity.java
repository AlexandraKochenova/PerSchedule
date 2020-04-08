package com.example.client.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapters.ResponsibilityListAdapter;
import com.example.client.classes.Responsibility;
import com.example.client.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ScheduleActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecyclerView responsibilityList;
    private ResponsibilityListAdapter adapter;
    private FloatingActionButton addNewResponsibilityBtn;
    private LinearLayoutManager layoutManager;

    private int petId;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        petId = Integer.valueOf(getIntent().getStringExtra("petId"));
        responsibilityList = (RecyclerView) findViewById(R.id.responsibility_list);
        layoutManager = new LinearLayoutManager(this);
        responsibilityList.setLayoutManager(layoutManager);
        adapter = new ResponsibilityListAdapter(getApplicationContext(), initData());
        responsibilityList.setAdapter(adapter);
        addNewResponsibilityBtn = (FloatingActionButton) findViewById(R.id.add_new_responsibility_btn);
        addNewResponsibilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewResponsibilityActivity.class);
                intent.putExtra("petId", String.valueOf(petId));
                startActivity(intent);
            }
        });
    }

    private List<Responsibility> initData() {
        List<Responsibility> list = dbHelper.selectResponsibility(petId);
        return list;
    }



}
