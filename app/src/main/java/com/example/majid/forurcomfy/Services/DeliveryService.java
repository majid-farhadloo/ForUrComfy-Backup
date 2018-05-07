package com.example.majid.forurcomfy.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.majid.forurcomfy.Remote.MyWebService;

import java.io.IOException;

import LayOutReturn.DeliveryItem;
import retrofit2.Call;

/**
 * Created by farha on 4/22/2018.
 */

public class DeliveryService extends IntentService {

    public static final String TAG = "MyService";
    public static final String MY_SERVICE_MESSAGE = "MyServiceMessage";
    public static final String MY_SERVICE_PAYLOAD = "MyServicePayload";


    public DeliveryService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
        Call<DeliveryItem> call = myWebService.reqDelivery();
        DeliveryItem deliveryItem;
        try {
            deliveryItem = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }
//        Uri uri = intent.getData();
//        Log.i(TAG, "onHandleIntent: " + uri.toString());
//        String response;
//        try {
//            response  = HttpHelper.downloadUrl(uri.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        Gson gson  = new Gson();
//        ShoppingItem[] dataItems = gson.fromJson(response, ShoppingItem[].class);
//        Log.i("DATAPASS", "data passed :" + dataItems);
        Intent intentMessage = new Intent(MY_SERVICE_MESSAGE);
        intentMessage.putExtra(MY_SERVICE_PAYLOAD,  deliveryItem);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(intentMessage);
    }
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.i(TAG, "onCreate");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.i(TAG, "onDestroy");
//    }
}

