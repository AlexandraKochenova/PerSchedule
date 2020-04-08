package com.example.client.fragments;

import android.content.Intent;
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
import com.example.client.activities.NewProductActivity;
import com.example.client.classes.Feeding;
import com.example.client.classes.Product;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class FeedingFragment extends Fragment {

    private TimePicker time;
    private Spinner feedList;
    private EditText weightText;
    private Spinner periodText;
    private Button saveButton;
    private ImageView addNewProduct;

    private DatabaseHelper dbHelper;
    private Product product;
    private int period;
    private int petId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeding, container, false);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        time = (TimePicker) view.findViewById(R.id.feeding_fragment_time_picker);
        time.setIs24HourView(true);
        feedList = (Spinner) view.findViewById(R.id.feeding_fragment_list_feed);
        weightText = (EditText) view.findViewById(R.id.feeding_fragment_weight);
        periodText = (Spinner) view.findViewById(R.id.feeding_fragment_list_period);
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

        addNewProduct = (ImageView) view.findViewById(R.id.feeding_fragment_add_new_product);
        saveButton = (Button) view.findViewById(R.id.feeding_fragment_save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = time.getCurrentHour();
                int minutes = time.getCurrentMinute();
                long x = Constants.currentDate();
                x += (hours * 60 + minutes)*60*1000;
                Date date = new Date(x);
                int weight = Integer.valueOf(weightText.getText().toString());
                Feeding feeding = new Feeding(petId, Constants.RESPONSIBILITY_CODE_FEDDING, date, period, product, weight);
                long result = dbHelper.saveNewResponsibility(feeding);
                if (result < 0) {
                    Toast.makeText(getActivity().getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                }
                getActivity().onBackPressed();
            }
        });

        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NewProductActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayAdapter<?> adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, productDataString());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feedList.setAdapter(adapter);
        feedList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                product = productData().get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public List<String> productDataString(){
        List<Product> list = dbHelper.selectProducts();
        List<String> newList = new ArrayList<>();
        for (Product product : list) {
            newList.add(product.getName());
        }
        return newList;
    }
    public List<Product> productData(){
        List<Product> list = dbHelper.selectProducts();
        return list;
    }

    public void setPetId(int petId){
        this.petId = petId;
    }

}
