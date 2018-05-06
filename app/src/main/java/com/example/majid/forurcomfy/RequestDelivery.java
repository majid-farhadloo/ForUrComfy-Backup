//package com.example.majid.forurcomfy;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.content.LocalBroadcastManager;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.majid.forurcomfy.Remote.APIService;
//import com.example.majid.forurcomfy.Remote.ApiUtlis;
//import com.example.majid.forurcomfy.Services.DeliveryService;
//import com.example.majid.forurcomfy.ShoppingCart.ShoppingCartAdapter;
//import com.example.majid.forurcomfy.ShoppingCart.ShoppingItem;
//import com.example.majid.forurcomfy.Utlis.NetworkHelper;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class RequestDelivery extends AppCompatActivity {
//    ShoppingCartAdapter mItemAdapter;
//    List<ShoppingItem> mItemList;
//    ListView listView;
//    public boolean networkOk;
//    public static final String JSON_URL = "https://node-practice0208.herokuapp.com/delivery";
//
//    private APIService mAPIService;
//
//
//    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ShoppingItem[] mItemList = (ShoppingItem[]) intent
//                    .getParcelableArrayExtra(DeliveryService.MY_SERVICE_PAYLOAD);
//            Toast.makeText(RequestDelivery.this,
//                    "Received " + mItemList.length + " items from service",
//                    Toast.LENGTH_SHORT).show();
//            //mItemList = Arrays.asList(dataItems);
//            displayDataItems(null);
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_d_r);
//        networkOk = NetworkHelper.hasNetworkAccess(this);
//        if (networkOk) {
//            Intent intent = new Intent(this, DeliveryService.class);
//            intent.setData(Uri.parse(JSON_URL));
//            startService(intent);
//        } else {
//            Toast.makeText(this, "Network is not available", Toast.LENGTH_SHORT).show();
//        }
//        mAPIService = ApiUtlis.getAPIService();
//        // need to change
//        listView =  findViewById(R.id.rvItems);
//        // need to change
//        LocalBroadcastManager.getInstance(getApplicationContext())
//                .registerReceiver(mBroadcastReceiver,
//                        new IntentFilter(DeliveryService.MY_SERVICE_MESSAGE));
//        reqDelivery();
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        LocalBroadcastManager.getInstance(getApplicationContext()).
//                unregisterReceiver(mBroadcastReceiver);
//    }
//
//
//    private void displayDataItems(String category) {
//
//
//        if (mItemList != null) {
//            mItemAdapter = new ShoppingCartAdapter(this, mItemList);
//            listView.setAdapter(mItemAdapter);
//        }
//    }
//
//
//    private void reqDelivery() {
//        mAPIService.reqDelivery().enqueue(new Callback<List<ShoppingItem>>() {
//            @Override
//            public void onResponse(Call<List<ShoppingItem>> call, Response<List<ShoppingItem>> response) {
//                if (response.isSuccessful()) {
//                    Log.d("+++", "Success");
//                    mItemList = response.body();
//
//                    mItemAdapter = new ShoppingCartAdapter(getApplicationContext(), mItemList);
//                    listView.setAdapter(mItemAdapter);
////                    mRecyclerView.set(mItemAdapter);
//                } else {
//                    Log.d("+++", "Fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ShoppingItem>> call, Throwable t) {
//                Log.d("+++", "Fail");
//            }
//        });
//    }
//}
