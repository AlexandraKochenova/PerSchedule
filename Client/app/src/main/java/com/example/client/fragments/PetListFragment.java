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
import com.example.client.activities.NewPetActivity;
import com.example.client.adapters.PetAdapter;
import com.example.client.classes.Pet;
import com.example.client.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class PetListFragment extends Fragment {

    private RecyclerView petsList;
    private DatabaseHelper dbHelper;
    private PetAdapter petAdapter;
    private FloatingActionButton addNewPetBtn;
    private LinearLayoutManager layoutManager;
    private List<Pet> pets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pets_list, container, false);
        petsList = (RecyclerView) view.findViewById(R.id.pets_list);
        layoutManager = new LinearLayoutManager(view.getContext());
        petsList.setLayoutManager(layoutManager);
        addNewPetBtn = (FloatingActionButton) view.findViewById(R.id.add_new_pet_btn);
        addNewPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPet();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pets = new ArrayList<>();
        petAdapter = new PetAdapter(getContext(), pets);
        petsList.setAdapter(petAdapter);
    }


    private void addNewPet(){
        Intent intent = new Intent(getContext(), NewPetActivity.class);
        startActivity(intent);
    }

}
