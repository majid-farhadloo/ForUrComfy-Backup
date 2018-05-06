package com.example.majid.forurcomfy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        CardForm cardform = (CardForm)findViewById(R.id.cardform);
        TextView textDes = (TextView)findViewById(R.id.payment_amount);
        Button payBtn  = (Button)findViewById(R.id.btn_pay);

        textDes.setText("200$");
        payBtn.setText(String.format("Payer %s", textDes.getText()));

        cardform.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(PaymentActivity.this, "Number :" + card.getNumber() +
                        "|CVC: " +card.getCVC(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
