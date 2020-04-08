package com.example.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;
import com.example.client.helpers.NetworkService;
import com.example.client.requests.AuthPost;
import com.example.client.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthPost data = new AuthPost();
                data.setLogin(login.getText().toString());
                data.setPassword(password.getText().toString());
                NetworkService.getInstance()
                        .getJSONApi()
                        .authorizationUser(data)
                        .enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                UserResponse post = response.body();
                                if (post.getStatus() == Constants.OK) {
                                    DatabaseHelper _dbHelper = new DatabaseHelper(getApplicationContext());
                                    long result = _dbHelper.authUser(post.getUser());
                                    if (result <= 0) {
                                        Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        petListOpen();
                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }



    public void petListOpen(){
        Intent intent = new Intent(getApplicationContext(), PetListActivity.class);
        startActivity(intent);
    }
}
