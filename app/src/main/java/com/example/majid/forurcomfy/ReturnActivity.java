package com.example.majid.forurcomfy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.majid.forurcomfy.Data.model.FoodMenu;
import com.example.majid.forurcomfy.Remote.APIService;
import com.example.majid.forurcomfy.Remote.ApiUtlis;
import com.example.majid.forurcomfy.Services.MyService;
import com.example.majid.forurcomfy.Utlis.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnActivity extends AppCompatActivity {
    public boolean networkOk;
    List<FoodMenu> mItemList;
   // List<DataItem> dataItemList;
    DataItemAdapter mItemAdapter;
    RecyclerView mRecyclerView;
    public static final String JSON_URL = "https://node-practice0208.herokuapp.com/food_menu/";

    private APIService mAPIService;


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            FoodMenu[] mItemList = (FoodMenu[]) intent.getParcelableArrayExtra(MyService.MY_SERVICE_PAYLOAD);
            Toast.makeText(ReturnActivity.this,
                    "Received " + mItemList.length + " items from service",
                    Toast.LENGTH_SHORT).show();
            //mItemList = Arrays.asList(dataItems);
            displayDataItems(null);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        networkOk = NetworkHelper.hasNetworkAccess(this);
        if (networkOk) {
            Intent intent = new Intent(this, MyService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        } else {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_SHORT).show();
        }


        mAPIService = ApiUtlis.getAPIService();

        mRecyclerView = (RecyclerView) findViewById(R.id.rvItems);
//        if (true) {
//            //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        }

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(MyService.MY_SERVICE_MESSAGE));
        reqData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).
                unregisterReceiver(mBroadcastReceiver);
    }


    private void displayDataItems(String category) {


        if (mItemList != null) {
            mItemAdapter = new DataItemAdapter(this, mItemList);
            mRecyclerView.setAdapter(mItemAdapter);
        }
    }


    private void reqData() {
        mAPIService.reqData().enqueue(new Callback<List<FoodMenu>>() {
            @Override
            public void onResponse(Call<List<FoodMenu>> call, Response<List<FoodMenu>> response) {
                if (response.isSuccessful()) {
                    Log.d("+++", "Success");
                    mItemList = response.body();

                    mItemAdapter = new DataItemAdapter(getApplicationContext(), mItemList);
                    mRecyclerView.setAdapter(mItemAdapter);
//                    mRecyclerView.set(mItemAdapter);
                } else {
                    Log.d("+++", "Fail");
                }
            }

            @Override
            public void onFailure(Call<List<FoodMenu>> call, Throwable t) {
                Log.d("+++", "Fail");
            }
        });
    }

}
//    private class MyTaskLoader extends AsyncTaskLoader<String> {
//
//        public MyTaskLoader(Context context) {
//            super(context);
//        }
//
//        @Override
//        public String loadInBackground() {
//            return null;
//        }
//    }


