package com.example.client.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.client.R;
import com.example.client.activities.NewResponsibilityActivity;
import com.example.client.adapters.ResponsibilityListAdapter;
import com.example.client.classes.Responsibility;
import com.example.client.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ResponsibilitiesFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private RecyclerView responsibilityList;
    private ResponsibilityListAdapter adapter;
    private FloatingActionButton addNewResponsibilityBtn;
    private LinearLayoutManager layoutManager;

    private List<Responsibility> responsibilitiesList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_responsibilities, container, false);
        dbHelper = new DatabaseHelper(view.getContext());
        responsibilityList = (RecyclerView) view.findViewById(R.id.responsibility_list);
        layoutManager = new LinearLayoutManager(getContext());
        responsibilityList.setLayoutManager(layoutManager);

        addNewResponsibilityBtn = (FloatingActionButton) view.findViewById(R.id.add_new_responsibility_btn);
        addNewResponsibilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewResponsibilityActivity.class);
                intent.putExtra("petId", String.valueOf(0));
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        responsibilitiesList = new ArrayList<>();
        adapter = new ResponsibilityListAdapter(getContext(), initData());
        responsibilityList.setAdapter(adapter);

    }

    private List<Responsibility> initData() {
        List<Responsibility> list = dbHelper.selectResponsibility(-1);
        return list;
    }


}
