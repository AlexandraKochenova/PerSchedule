package com.example.client.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapters.FamilyMemberAdapter;
import com.example.client.classes.User;
import com.example.client.helpers.DatabaseHelper;

import java.util.List;

public class FamilyListActivity extends AppCompatActivity {

    private RecyclerView familyList;
    private DatabaseHelper dbHelper;
    private FamilyMemberAdapter famAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_family_list);
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        familyList = (RecyclerView) findViewById(R.id.family_list_view);
        layoutManager = new LinearLayoutManager(this);
        familyList.setLayoutManager(layoutManager);
        famAdapter = new FamilyMemberAdapter(getApplicationContext(), initData());
        familyList.setAdapter(famAdapter);

    }

    private List<User> initData(){
        List<User> list = dbHelper.selectFamily();
        return list;
    }

}
