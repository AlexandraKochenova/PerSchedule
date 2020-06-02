package com.example.client.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.client.R;
import com.example.client.adapters.ResponsibilityListAdapter;
import com.example.client.classes.FamilyUserRelation;
import com.example.client.classes.Pet;
import com.example.client.classes.Responsibility;
import com.example.client.classes.ServerResponsibility;
import com.example.client.classes.ServerThing;
import com.example.client.classes.Thing;
import com.example.client.classes.User;
import com.example.client.fragments.FamilyListFragment;
import com.example.client.fragments.PetListFragment;
import com.example.client.fragments.ResponsibilitiesFragment;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;
import com.example.client.helpers.NetworkService;
import com.example.client.helpers.ServerToClientHelper;
import com.example.client.responses.AllFriendshipResponse;
import com.example.client.responses.AllPetsResponse;
import com.example.client.responses.AllResponsibilitiesResponse;
import com.example.client.responses.AllThingsResponse;
import com.example.client.responses.FamilyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ImageView petsList;
    private ImageView responsibilityList;
    private ImageView thingsList;
    private ImageView familyMembersList;
    private DatabaseHelper dbHelper;
    private int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(getApplicationContext());
        userID = dbHelper.getCurrentUserID();
        petsList = (ImageView) findViewById(R.id.menu_pets_list_btn);
        responsibilityList = (ImageView) findViewById(R.id.menu_responsibility_list_btn);
        thingsList = (ImageView) findViewById(R.id.menu_things_list_btn);
        familyMembersList = (ImageView) findViewById(R.id.menu_family_members_list_btn);

        petsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetListFragment fragment = new PetListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_menu_layout, fragment)
                        .commit();
            }
        });

        responsibilityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResponsibilitiesFragment fragment = new ResponsibilitiesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_menu_layout, fragment)
                        .commit();
            }
        });

        thingsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        familyMembersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FamilyListFragment fragment = new FamilyListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_menu_layout, fragment)
                        .commit();
            }
        });

        if (savedInstanceState == null) {
            PetListFragment fragment = new PetListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_menu_layout, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper.onUpgrade(dbHelper.getReadableDatabase(), 0, 1);
        getFamilyFromServer();
        getAllFriendshipsFromServer();
        getAllPetsFromServer();
        getAllThingsFromServer();
        getAllResponsibilitiesFromServer();
    }

    private void getAllFriendshipsFromServer(){
        NetworkService.getInstance()
                .getJSONApi()
                .getNewFriendships(String.valueOf(dbHelper.getCurrentUserID()))
                .enqueue(new Callback<AllFriendshipResponse>() {
                    @Override
                    public void onResponse(Call<AllFriendshipResponse> call, Response<AllFriendshipResponse> response) {
                        AllFriendshipResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            for (FamilyUserRelation fam : post.getNewFriendshipList()) {
                                dbHelper.addNewFriendship(fam);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllFriendshipResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getAllResponsibilitiesFromServer() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllResponsibilities(String.valueOf(userID))
                .enqueue(new Callback<AllResponsibilitiesResponse>() {
                    @Override
                    public void onResponse(Call<AllResponsibilitiesResponse> call, Response<AllResponsibilitiesResponse> response) {
                        AllResponsibilitiesResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            for (ServerResponsibility serverResponsibility : post.getServerResponsibilityList()) {
                                Responsibility responsibility = ServerToClientHelper.toClientResponsibility(serverResponsibility);
                                dbHelper.saveNewResponsibility(responsibility);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllResponsibilitiesResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getAllThingsFromServer() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllThings(String.valueOf(dbHelper.getCurrentUserID()))
                .enqueue(new Callback<AllThingsResponse>() {
                    @Override
                    public void onResponse(Call<AllThingsResponse> call, Response<AllThingsResponse> response) {
                        AllThingsResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            for (ServerThing serverThing : post.getThingsList()) {
                                Thing thing = ServerToClientHelper.toClientThing(serverThing);
                                long result = dbHelper.saveNewThing(thing);
                                if (result <= 0) {
                                    Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllThingsResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getAllPetsFromServer() {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllPets(String.valueOf(userID))
                .enqueue(new Callback<AllPetsResponse>() {
                    @Override
                    public void onResponse(Call<AllPetsResponse> call, Response<AllPetsResponse> response) {
                        AllPetsResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            for (Pet serverPet : post.getPetsList()) {
                                long result = dbHelper.saveNewPet(serverPet);
                                if (result <=0) {
                                    Toast.makeText(getApplicationContext(),"Failed, Try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllPetsResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getFamilyFromServer(){
        int id = dbHelper.getFamId();
        NetworkService.getInstance()
                .getJSONApi()
                .getFamilyMembers(String.valueOf(id))
                .enqueue(new Callback<FamilyResponse>() {
                    @Override
                    public void onResponse(Call<FamilyResponse> call, Response<FamilyResponse> response) {
                        FamilyResponse post = response.body();
                        if (post.getStatus() == Constants.OK) {
                            for (User user : post.getFamilyList()) {
                                if (user.getId() == dbHelper.getCurrentUserID()) {
                                    continue;
                                }
                                long result = dbHelper.updateFam(user);
                                if (result <= 0) {
                                    Toast.makeText(getApplicationContext(), Constants.FAILED, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), post.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FamilyResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });

    }

}
