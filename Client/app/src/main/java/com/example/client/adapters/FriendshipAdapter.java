package com.example.client.adapters;

import android.content.Context;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.classes.FamilyUserRelation;
import com.example.client.classes.User;
import com.example.client.helpers.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendshipAdapter extends RecyclerView.Adapter<FriendshipAdapter.ViewHolder> {


    private List<FamilyUserRelation> friendshipsList;
    private Context context;
    private Boolean isInput;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView userID;
        private ImageView addBtn;
        private ImageView deleteBtn;

        public ViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.item_friendship_name_text);
            userID = (TextView) view.findViewById(R.id.item_friendship_id_text);
            addBtn = (ImageView) view.findViewById(R.id.item_friendship_add_btn);
            deleteBtn = (ImageView) view.findViewById(R.id.item_friendship_delete_btn);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friendship, parent, false);
        FriendshipAdapter.ViewHolder vh = new FriendshipAdapter.ViewHolder(view);
        return vh;
    }

    public FriendshipAdapter(Context context, List<FamilyUserRelation> list, Boolean isInputFriendship) {
        this.isInput = isInputFriendship;
        this.friendshipsList = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return friendshipsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FamilyUserRelation relation = friendshipsList.get(position);
        User user = isInput ? relation.getUserFirst() : relation.getUserSecond();
        holder.userID.setText(user.getId());
        String userName = user.getName() + " " + user.getLastName();
        holder.userName.setText(userName);
        holder.addBtn.setVisibility(isInput? View.VISIBLE : View.GONE);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFamilyMember(relation.getID());
            }
        });

        holder.deleteBtn.setVisibility(isInput? View.GONE : View.VISIBLE);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFriendship(relation.getID());
            }
        });
    }


    private void addNewFamilyMember(int id) {
        NetworkService.getInstance()
                .getJSONApi()
                .addNewFamilyMember(String.valueOf(id))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

    private void deleteFriendship(int id) {
        NetworkService.getInstance()
                .getJSONApi()
                .deleteNewFriendship(String.valueOf(id))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

}
