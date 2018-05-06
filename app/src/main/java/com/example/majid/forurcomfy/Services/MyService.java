package com.example.majid.forurcomfy.Services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.majid.forurcomfy.Data.model.FoodMenu;
import com.example.majid.forurcomfy.Utlis.HttpHelper;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by farha on 4/22/2018.
 */

public class MyService extends IntentService {

    public static final String TAG = "MyService";
    public static final String MY_SERVICE_MESSAGE = "MyServiceMessage";
    public static final String MY_SERVICE_PAYLOAD = "MyServicePayload";


    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: " + uri.toString());
        String response;
        try {
            response  = HttpHelper.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Gson gson  = new Gson();
        FoodMenu[] dataItems = gson.fromJson(response, FoodMenu[].class);
        Log.i("DATAPASS", "data passed :" + dataItems);
        Intent intentMessage  = new Intent (MY_SERVICE_MESSAGE);
        intentMessage.putExtra(MY_SERVICE_PAYLOAD,dataItems);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(intentMessage);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}