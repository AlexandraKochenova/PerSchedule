package com.example.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.classes.thingsclasses.Product;
import com.example.client.classes.Responsibility;
import com.example.client.helpers.Constants;
import com.example.client.helpers.DatabaseHelper;

import java.util.Date;
import java.util.List;

public class ResponsibilityListAdapter extends RecyclerView.Adapter<ResponsibilityListAdapter.ViewHolder>{

    private List<Responsibility> responsibilityList;
    private Context context;
    private DatabaseHelper dbHelper;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView responsibilityName;
        private TextView time;
        private TextView information;
        private CheckBox responsiilityDone;

        public ViewHolder(View view) {
            super(view);
            responsibilityName = (TextView) view.findViewById(R.id.responsibility_name);
            time = (TextView) view.findViewById(R.id.responsibility_time_text);
            information = (TextView) view.findViewById(R.id.responsibility_product);
            responsiilityDone = (CheckBox) view.findViewById(R.id.responsibility_done_check_box);
        }
    }

    @Override
    public ResponsibilityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_responsibility, parent, false);
        ResponsibilityListAdapter.ViewHolder vh = new ResponsibilityListAdapter.ViewHolder(view);
        return vh;
    }

    public ResponsibilityListAdapter(Context context, List<Responsibility> list){
        responsibilityList = list;
        this.context = context;
        dbHelper = new DatabaseHelper(this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Responsibility item = responsibilityList.get(position);
        holder.responsibilityName.setText(item.getName());
        holder.time.setText(item.getResponsibilityTimeString());
        holder.information.setText(item.getInformationForActivity());
        holder.responsiilityDone.setChecked(isDone(item));
        holder.responsiilityDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentDate = new Date();
                Responsibility item = responsibilityList.get(position);
                long update = dbHelper.readyResponsibility(item.getId(),currentDate);
                if (update < 1)
                {
                    Toast.makeText(context, "Failed, try again", Toast.LENGTH_LONG).show();
                }
                if (item.getName().equals(Constants.RESPONSIBILITY_CODE_FEDDING)) {
                    List<Product> list = dbHelper.selectProducts();
                    Product product = null;
                    for (Product pro : list) {
                        if (Integer.valueOf(item.getInformation().split("@")[0]) == pro.getId()){
                            product = pro;
                            break;
                        }
                    }
                    if (product != null) {
                        double pick = (double)Integer.valueOf(item.getInformation().split("@")[1])/1000;

                        product.setStore(pick);
                        int x = dbHelper.pickStore(product);
                        if (x < 1) {
                            Toast.makeText(context, "Failed, try again", Toast.LENGTH_LONG).show();
                        }
                        if (product.getStore() < 0.2) {
                            Toast.makeText(context, "Заканчиваются запасы: " + product.getName() + ", осталось " + product.getStore(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return responsibilityList.size();
    }

    public boolean isDone(Responsibility item){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        return dbHelper.doneResponsibilityForTime(item.getId(), item.getResponsibilityDate()) > -1 ? true : false;
    }
}
