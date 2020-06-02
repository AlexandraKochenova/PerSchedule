package com.example.client.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.client.R;
import com.example.client.classes.responsibilitiesclasses.Walk;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;

import java.util.Date;

public class WalkFragment extends Fragment {

    private EditText time;
    private Button saveButton;
    private Spinner walkPeriod;
    private TimePicker timePick;

    private DatabaseHelper dbHelper;
    private int petId;
    private int period;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk, container, false);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        time = (EditText) view.findViewById(R.id.time_for_walk_input);
        walkPeriod = (Spinner) view.findViewById(R.id.walk_fragment_list_period);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.period_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        walkPeriod.setAdapter(adapter);
        walkPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                period = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timePick = (TimePicker) view.findViewById(R.id.walk_fragment_time);
        timePick.setIs24HourView(true);
        saveButton = (Button) view.findViewById(R.id.walk_fragment_save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: сохранить responsibility и отправить в бд, уйти в список responsibility
                int hours = timePick.getCurrentHour();
                int minutes = timePick.getCurrentMinute();
                long x = Constants.currentDate();
                x += (hours * 60 + minutes)*60*1000;
                Date date = new Date(x);
                int timeForWalk = Integer.valueOf(time.getText().toString());
                Walk walk = new Walk(petId, Constants.RESPONSIBILITY_CODE_WALK, date, period, timeForWalk);
                long result = dbHelper.saveNewResponsibility(walk);
                if (result<0) {
                    Toast.makeText(getActivity().getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                }
                getActivity().onBackPressed();
            }
        });


        return view;
    }

    public void setPetId(int petId){
        this.petId = petId;
    }

}
