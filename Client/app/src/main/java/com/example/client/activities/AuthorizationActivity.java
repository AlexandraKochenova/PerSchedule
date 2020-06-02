package com.example.client.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.client.R;
import com.example.client.classes.User;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;
import com.example.client.helpers.NetworkService;
import com.example.client.requests.AuthPost;
import com.example.client.responses.FamilyResponse;
import com.example.client.responses.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationActivity extends AppCompatActivity {

    private RelativeLayout auth;
    private EditText login;
    private EditText password;
    private Button signToRegistatration;
    private DatabaseHelper dbHelper;

    private RelativeLayout registration;
    private EditText nameRegistration;
    private EditText loginRegistration;
    private EditText passwordRegistration;
    private EditText familyRegistration;
    private Button registatrationToSign;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        dbHelper = new DatabaseHelper(getApplicationContext());

        auth = (RelativeLayout) findViewById(R.id.sign_in_fragment) ;
        registration = (RelativeLayout) findViewById(R.id.sign_up_fragment);
        signToRegistatration = (Button) findViewById(R.id.not_login_to_registration);
        signToRegistatration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                auth.setVisibility(View.GONE);
                registration.setVisibility(View.VISIBLE);
            }
        });

        registatrationToSign = (Button) findViewById(R.id.not_registration_to_login);
        registatrationToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.setVisibility(View.VISIBLE);
                registration.setVisibility(View.GONE);
            }
        });


        login = (EditText) findViewById(R.id.login_auth);
        password = (EditText) findViewById(R.id.password_auth);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autorization(login.getText().toString(),password.getText().toString());
            }
        });

        nameRegistration = (EditText) findViewById(R.id.user_name_registration_input);
        loginRegistration = (EditText) findViewById(R.id.login_registration);
        passwordRegistration = (EditText) findViewById(R.id.password_registration);
        familyRegistration = (EditText) findViewById(R.id.user_family_registration);
        findViewById(R.id.registration_save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User serverUser = new User();
                serverUser.setId(0);
                serverUser.setFamilyId(-1);
                serverUser.setLogin(loginRegistration.getText().toString());
                serverUser.setPassword(passwordRegistration.getText().toString());
                serverUser.setName(nameRegistration.getText().toString());
                serverUser.setLastName(familyRegistration.getText().toString());
                registration(serverUser);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: если в бд есть данные пользователя, то авторизироваться
    }

    public void registration(final User serverUser) {
        NetworkService.getInstance()
                .getJSONApi()
                .registrationUser(serverUser)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() == null) {
                            Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (response.body().equals(Constants.USER_CREATED)) {
                            Toast.makeText(getApplicationContext(),Constants.USER_CREATED, Toast.LENGTH_SHORT).show();
                            autorization(serverUser.getLogin(), serverUser.getPassword());
                        }
                        else {
                            if (response.body().equals(Constants.USER_ALREADY_EXISTS)) {
                                Toast.makeText(getApplicationContext(),Constants.USER_ALREADY_EXISTS, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),response.body(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void autorization(String login, String password){
        AuthPost data = new AuthPost();
        data.setLogin(login);
        data.setPassword(password);
        NetworkService.getInstance()
                .getJSONApi()
                .authorizationUser(data)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            long result = dbHelper.authUser(post.getServerUser());
                            if (result <= 0) {
                                Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                openMainMenu();
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

    public void openMainMenu(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
