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
import com.example.client.activities.FriendshipListActivity;
import com.example.client.adapters.FamilyMemberAdapter;
import com.example.client.classes.User;
import com.example.client.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FamilyListFragment extends Fragment {

    private RecyclerView familyList;
    private DatabaseHelper dbHelper;
    private FamilyMemberAdapter famAdapter;
    private FloatingActionButton friendshipList;
    private LinearLayoutManager layoutManager;
    private List<User> family;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_list, container, false);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        familyList = (RecyclerView) view.findViewById(R.id.family_list_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        familyList.setLayoutManager(layoutManager);
        friendshipList = (FloatingActionButton) view.findViewById(R.id.friendship_list_activity_btn);
        friendshipList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFriendshipList();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        famAdapter = new FamilyMemberAdapter(getContext(), family);
        familyList.setAdapter(famAdapter);
    }

    private void initData(){
        family = dbHelper.selectFamily();
    }

    private void openFriendshipList(){
        Intent intent = new Intent(getContext(), FriendshipListActivity.class);
        startActivity(intent);
    }


}
