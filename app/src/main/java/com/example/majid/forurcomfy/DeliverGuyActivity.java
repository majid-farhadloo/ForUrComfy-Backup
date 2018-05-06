package com.example.majid.forurcomfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DeliverGuyActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    private Switch switchCase;
    private TextView tvSate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        switchCase = findViewById(R.id.switch1);
        tvSate = findViewById(R.id.tvSate);
        switchCase.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (switchCase.isChecked()) {
            Intent requestDelivery = new Intent(DeliverGuyActivity.this,RequestDeliveryItem.class);
            DeliverGuyActivity.this.startActivity(requestDelivery);
            finish();
            tvSate.setText("The Switch Is On");
            Toast.makeText(getApplicationContext(), " The Switch is on",
                    Toast.LENGTH_SHORT).show();
        } else {
            tvSate.setText("The Switch is off");
            Toast.makeText(getApplicationContext(), "The Switch is OFF",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

