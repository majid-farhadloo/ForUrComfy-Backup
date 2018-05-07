package com.example.majid.forurcomfy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.majid.forurcomfy.Remote.APIService;
import com.example.majid.forurcomfy.Remote.ApiUtlis;
import com.example.majid.forurcomfy.Services.DeliveryService;
import com.example.majid.forurcomfy.Utlis.NetworkHelper;

import LayOutReturn.DeliveryItem;
import LayOutReturn.DeliveryItemListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestDeliveryItem extends AppCompatActivity {
    //newShoppingCartAdapter mItemAdapter;
    DeliveryItemListAdapter mItemAdapter;
    // List<ShoppingItem> mItemList;
    DeliveryItem mItemList;
    ListView listView;
    //private TextView currentUser, address,userId,cellPhone;

    public boolean networkOk;
    public static final String JSON_URL = "https://node-practice0208.herokuapp.com/delivery/request";

    private APIService mAPIService;
    Button claimBtn;
    //Button rejectBtn;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DeliveryItem[] mItemList = (DeliveryItem[]) intent
                    .getParcelableArrayExtra(DeliveryService.MY_SERVICE_PAYLOAD);
            Toast.makeText(RequestDeliveryItem.this,
                    "Received " + mItemList.length + " items from service",
                    Toast.LENGTH_SHORT).show();
            //mItemList = Arrays.asList(dataItems);
            displayDataItems(null);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_r);
        claimBtn = (Button) findViewById(R.id.claim);
      //  rejectBtn = (Button) findViewById(R.id.reject);


//        currentUser = (TextView) findViewById(R.id.currentUser);
//        address = (TextView) findViewById(R.id.location);
//        cellPhone = (TextView) findViewById(R.id.cell);
//        userId = (TextView) findViewById(R.id.id);



        networkOk = NetworkHelper.hasNetworkAccess(this);
        if (networkOk) {
            Intent intent = new Intent(this, DeliveryService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        } else {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_SHORT).show();
        }
        mAPIService = ApiUtlis.getAPIService();
        // need to change
        // listView =  findViewById(R.id.rvItems);
        // need to change
//        mRecyclerView = (RecyclerView) findViewById(R.id.testView);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(DeliveryService.MY_SERVICE_MESSAGE));
        reqDelivery();


        claimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RequestDeliveryItem.this);
        alertDialog.setTitle("For last Step");
        alertDialog.setMessage("Please Verify if you want to deliver " +
                "delivered");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent claimedIntent = new Intent(RequestDeliveryItem.
                        this, Home.class);

                RequestDeliveryItem.this.startActivity(claimedIntent);
                Toast.makeText(getApplicationContext(), "Thanks for your claim",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent claimedIntent = new Intent(RequestDeliveryItem.
                        this, Home.class);
                RequestDeliveryItem.this.startActivity(claimedIntent);
                Toast.makeText(getApplicationContext(), "You rejected item",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).
                unregisterReceiver(mBroadcastReceiver);
    }


    private void displayDataItems(String category) {


        if (mItemList != null) {
            mItemAdapter = new DeliveryItemListAdapter(this, mItemList,mAPIService);
            listView.setAdapter(mItemAdapter);
        }
    }


    private void reqDelivery() {
//        Intent intent = new Intent(this,DeliveryService.class);
//        startService(intent);
        mAPIService.reqDelivery().enqueue(new Callback<DeliveryItem>() {
            @Override
            public void onResponse(Call<DeliveryItem> call, Response<DeliveryItem> response) {
                if (response.isSuccessful()) {
                    Log.d("delivery", response.toString());
                    mItemList = response.body();

                    //TODO making new adapter based on the new layout that I have;
                    mItemAdapter = new DeliveryItemListAdapter(getApplicationContext(), mItemList,mAPIService);
                    listView.setAdapter(mItemAdapter);
//                    mRecyclerView.set(mItemAdapter);
                } else {
                    String msg;
                   msg = String.valueOf(response.body());
                    Log.d("+++", "Fail");
                }
            }

            @Override
            public void onFailure(Call<DeliveryItem> call, Throwable t) {
                Log.d("+++", "Fail");
            }
        });
    }
}
