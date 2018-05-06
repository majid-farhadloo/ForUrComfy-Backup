package com.example.majid.forurcomfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        final EditText emailConfirm = (EditText) findViewById(R.id.EmailConfirm);
        final Button confrimbtn = (Button) findViewById(R.id.confirm);
    }
}
