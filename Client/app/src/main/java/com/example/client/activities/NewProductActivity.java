package com.example.client.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.classes.Product;
import com.example.client.helpers.DatabaseHelper;

public class NewProductActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private EditText productName;
    private EditText productCaloric;
    private EditText productStore;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        productName = (EditText) findViewById(R.id.new_product_name);
        productCaloric = (EditText) findViewById(R.id.new_product_caloric);
        productStore = (EditText) findViewById(R.id.new_product_store);
        saveButton = (Button) findViewById(R.id.new_product_save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString();
                int caloric;
                double store;
                try {
                    caloric = Integer.getInteger(productCaloric.getText().toString());
                }
                catch (Exception e) {
                    caloric = 0;
                }
                try {
                    store = Double.valueOf(productStore.getText().toString());
                }
                catch (Exception e) {
                    store = 0.0;
                }

                Product product = new Product(name, caloric, store);
                long result = dbHelper.saveNewProduct(product);
                if (result <= 0) {
                    Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

}
