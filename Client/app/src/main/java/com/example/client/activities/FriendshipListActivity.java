package com.example.client.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.client.R;
import com.example.client.adapters.FriendshipAdapter;
import com.example.client.classes.FamilyUserRelation;
import com.example.client.classes.User;
import com.example.client.helpers.DatabaseHelper;
import com.example.client.helpers.NetworkService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendshipListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecyclerView inputFriendshipList;
    private RecyclerView outputFriendshipList;
    private FriendshipAdapter inputAdapter;
    private FriendshipAdapter outputAdapter;
    private LinearLayoutManager layoutManager;
    private List<FamilyUserRelation> inputList;
    private List<FamilyUserRelation> outputList;
    private FloatingActionButton addNewFamily;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendships);
        dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        inputFriendshipList = (RecyclerView) findViewById(R.id.friendships_list_input);
        outputFriendshipList = (RecyclerView) findViewById(R.id.friendships_list_output);
        layoutManager = new LinearLayoutManager(this);
        initData();
        inputAdapter = new FriendshipAdapter(getApplicationContext(), inputList, true);
        inputFriendshipList.setAdapter(inputAdapter);
        outputAdapter = new FriendshipAdapter(getApplicationContext(), outputList, false);
        outputFriendshipList.setAdapter(outputAdapter);
        addNewFamily = (FloatingActionButton) findViewById(R.id.add_new_family_member_btn);
        addNewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View content = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_new_friendship,null);
                final EditText userId = (EditText) content.findViewById(R.id.friendship_input_id_edit);
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setView(content);
                final AlertDialog dialog = builder.create();
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addNewFamilyMember(userId.getText().toString());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void initData() {
        List<FamilyUserRelation> list = dbHelper.selectFriendships();
        outputList = new ArrayList<>();
        inputList = new ArrayList<>();
        int userID = dbHelper.getCurrentUserID();
        for (FamilyUserRelation familyUserRelation : list) {
            if (familyUserRelation.getUserFirst().getId() == userID) {
                outputList.add(familyUserRelation);
            }
            else {
                inputList.add(familyUserRelation);
            }
        }
    }

    private void addNewFamilyMember(String id){
        FamilyUserRelation fam = new FamilyUserRelation();
        fam.setUserFirst(new User(dbHelper.getCurrentUserID()));
        fam.setUserSecond(new User(Integer.valueOf(id)));
        NetworkService.getInstance()
                .getJSONApi()
                .addNewFriendship(fam)
                .enqueue(new Callback<FamilyUserRelation>() {
                    @Override
                    public void onResponse(Call<FamilyUserRelation> call, Response<FamilyUserRelation> response) {
                        dbHelper.addNewFriendship(response.body());
                        initData();
                    }

                    @Override
                    public void onFailure(Call<FamilyUserRelation> call, Throwable t) {

                    }
                });
    }
}
