package com.example.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.client.R;
import com.example.client.classes.Pet;

import java.util.List;

public class PetAdapter extends BaseAdapter {

    private List<Pet> _list;
    private LayoutInflater _layoutInflater;

    public PetAdapter(Context context, List<Pet> list) {
        _list = list;
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = _layoutInflater.inflate(R.layout.pet_item_layout, parent, false);
        }
        Pet pet = getPet(position);
        TextView petItemName = (TextView) view.findViewById(R.id.pet_item_name);
        petItemName.setText(pet.get_name());
        return view;
    }

    private Pet getPet(int position) {
        return (Pet) getItem(position);
    }

}
