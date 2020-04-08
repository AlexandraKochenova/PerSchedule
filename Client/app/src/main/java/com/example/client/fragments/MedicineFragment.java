package com.example.client.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.client.R;
import com.example.client.classes.Medicine;
import com.example.client.classes.Pill;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;

import java.util.Date;

public class MedicineFragment extends Fragment {

    private TimePicker time;
    private EditText pillChoice;
    private EditText value;
    private Spinner periodText;
    private Button saveButton;
//    private ImageView addNewPill;

    private DatabaseHelper dbHelper;
//    private Pill pill;
    private int period;
    private int petId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        time = (TimePicker) view.findViewById(R.id.medicine_fragment_time);
        time.setIs24HourView(true);
        pillChoice = (EditText) view.findViewById(R.id.medicine_fragment_pill_choice);
        periodText = (Spinner) view.findViewById(R.id.medicine_fragment_period_choice);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.period_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodText.setAdapter(adapter);
        periodText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                period = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        value = (EditText) view.findViewById(R.id.medicine_fragment_pill_value);
//        addNewPill = (ImageView) view.findViewById(R.id.medicine_fragment_add_new_pill);
        saveButton = (Button) view.findViewById(R.id.medicine_fragment_save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = time.getCurrentHour();
                int minutes = time.getCurrentMinute();
                long x = Constants.currentDate();
                x += (hours * 60 + minutes)*60*1000;
                Date date = new Date(x);
                String pillName = pillChoice.getText().toString();
                int pillValue = Integer.valueOf(value.getText().toString());
                Medicine medicine = new Medicine(petId, Constants.RESPONSIBILITY_CODE_MEDICINE, date, period, pillName, pillValue);
                long result = dbHelper.saveNewResponsibility(medicine);
                if (result < 0) {
                    Toast.makeText(getActivity().getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                }
                getActivity().onBackPressed();
            }
        });

//        addNewPill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent
//            }
//        });
        return view;
    }

    public void setPetId(int petId){
        this.petId = petId;
    }
}
