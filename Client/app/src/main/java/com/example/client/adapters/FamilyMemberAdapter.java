package com.example.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.classes.User;

import java.util.List;

public class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.ViewHolder> {

    private List<User> familyMembers;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView familyMemberName;
        public ViewHolder (View view) {
            super(view);
            familyMemberName = (TextView) view.findViewById(R.id.family_member_name);
        }
    }

    @Override
    public FamilyMemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_family_member, parent, false);
        FamilyMemberAdapter.ViewHolder vh = new FamilyMemberAdapter.ViewHolder(view);
        return vh;
    }

    public FamilyMemberAdapter(Context context, List<User> list){
        this.context = context;
        this.familyMembers = list;
    }

    @Override
    public int getItemCount() {
        return familyMembers.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User userItem = familyMembers.get(position);
        holder.familyMemberName.setText(userItem.getName());
        holder.familyMemberName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
