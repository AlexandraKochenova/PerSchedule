package com.example.client.activities;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.client.R;
import com.example.client.classes.Feeding;
import com.example.client.classes.Pet;
import com.example.client.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    private DatabaseHelper _dbHelper;
    private GridView _scheduleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        _dbHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        _scheduleItem = (GridView) findViewById(R.id.schedule_grid);



    }

    private Map<Date, Feeding> initData(){
        List<Pet> petList = _dbHelper.selectPets();
        Map<Date, Feeding> feedingList = new HashMap<Date, Feeding>();
        for (int i = 0; i < petList.size(); i++){
            Pet tempPet = petList.get(i);
            List<Feeding> tempFeedingList = tempPet.get_feedingList();
            while(tempFeedingList.size() > 0) {
                Feeding tmpFeeding = tempFeedingList.remove(0);
             //   feedingList.put(tmpFeeding.get_feedingTime(), tmpFeeding);
            }
        }
        return feedingList;
    }
}
