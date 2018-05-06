package com.example.majid.forurcomfy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class ThanksForShoppingActivity extends AppCompatActivity {

    RingProgressBar progressRing1,progressRing2;
    int progress = 0;
    @SuppressLint("HandlerLeak")
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0){
                if(progress<100){
                    progress++;
                    progressRing1.setProgress(progress);
                    progressRing2.setProgress(progress);

                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_for_shopping);
        progressRing1 = (RingProgressBar) findViewById(R.id.progressBar1);
        progressRing2 = (RingProgressBar) findViewById(R.id.progressBar2);

        progressRing1.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(ThanksForShoppingActivity.this, "Order is ready to PickUp !!!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    try {
                        Thread.sleep(200);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
