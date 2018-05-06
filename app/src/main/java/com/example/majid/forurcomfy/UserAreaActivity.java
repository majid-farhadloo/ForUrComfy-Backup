package com.example.majid.forurcomfy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.majid.forurcomfy.Data.model.FoodMenu;
import com.example.majid.forurcomfy.Sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserAreaActivity extends AppCompatActivity {

    List<FoodMenu> dataItemList = SampleDataProvider.dataItemList;
   // List<DataItem> mItemList;
    List<FoodMenu> mItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Collections.sort(dataItemList, new Comparator<FoodMenu>() {
            @Override
            public int compare(FoodMenu o1, FoodMenu o2) {
                return o1.itemName.compareTo(o2.itemName);
            }
        });

        DataItemAdapter adapter = new DataItemAdapter(this,dataItemList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
    }
}
