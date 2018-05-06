package com.example.majid.forurcomfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class CheckoutActivity extends AppCompatActivity {
    String price;

    public static final String PRICE = "PRICE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        CardForm cardform = (CardForm)findViewById(R.id.cardform);
        TextView textDes = (TextView)findViewById(R.id.payment_amount);
        Button payBtn  = (Button)findViewById(R.id.btn_pay);

        final Intent intent = getIntent();
        if (intent.hasExtra(PRICE) && getIntent().getExtras() != null) {
            price = getIntent().getExtras().getString(PRICE);

        }
        textDes.setText(price);
        payBtn.setText(String.format("Payer %s", textDes.getText()));

        cardform.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(CheckoutActivity.this, "Number :" + card.getNumber() +
                        "|CVC: " +card.getCVC(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
