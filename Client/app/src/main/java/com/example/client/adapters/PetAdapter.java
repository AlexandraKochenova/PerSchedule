package com.example.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.classes.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    private List<Pet> _list;
    private Context _context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView petName;
        private ImageView petBtn;

        public ViewHolder(View view) {
            super(view);
            petName = (TextView) view.findViewById(R.id.pet_item_name_text);
            petBtn = (ImageView) view.findViewById(R.id.pet_item_image_edit_view);
        }
    }

    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pet_item_layout, parent, false);
        PetAdapter.ViewHolder vh = new PetAdapter.ViewHolder(view);
        return vh;
    }

    public PetAdapter(Context context, List<Pet> list) {
        _list = list;
        _context = context;
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet petItem = _list.get(position);
        holder.petName.setText(petItem.get_name());
        holder.petName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: переход на окно расписания

            }
        });

        holder.petBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: вызов диалога редактирования питомца

            }
        });

    }
}
