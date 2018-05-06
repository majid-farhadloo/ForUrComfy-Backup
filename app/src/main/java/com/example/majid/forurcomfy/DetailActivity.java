//package com.example.android.data;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.android.data.model.DataItem;
//import com.example.android.data.sample.SampleDataProvider;
//
//public class DetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
////        String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
////        DataItem item = SampleDataProvider.dataItemMap.get(itemId);
//        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
//        if (item != null) {
//            Toast.makeText(this, "You Selected " + item.getItemName(),
//                    Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "Not Received Any Data", Toast.LENGTH_SHORT).show();
//        }
//
//
//
//    }
//}

package com.example.majid.forurcomfy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majid.forurcomfy.Data.model.FoodMenu;
import com.example.majid.forurcomfy.Data.model.SqliteHelper;
import com.example.majid.forurcomfy.Sample.SampleDataProvider;
import com.example.majid.forurcomfy.ShoppingCart.ShoppingCartWindow;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static java.sql.Types.NULL;

@SuppressWarnings("FieldCanBeLocal")
public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvPrice;
    private ImageView itemImage;
    List<FoodMenu> dataItemList = SampleDataProvider.dataItemList;
    private FoodMenu item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final SqliteHelper dbhelper = new SqliteHelper(getApplicationContext(), "ShoppingCart.db", null, 1);
//        FoodMenu item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item != null) {
            Toast.makeText(this, "Received item " + item.getItemId(),
                    Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(this, "Didn't receive any data", Toast.LENGTH_SHORT).show();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Shopping cart", "clicked: " + item);

                Intent shoppingIntent = new Intent(DetailActivity.
                        this,ShoppingCartWindow.class);
                startActivity(shoppingIntent);
                Snackbar.make(view, "Go to Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton cart = (FloatingActionButton) findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbhelper.insert(NULL,item.getItemId(),1, item.getItemName(), item.getCategory(), item.getDescription(), item.getPrice());

                Snackbar.make(view, "Added to Your Shopping Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        tvName = (TextView) findViewById(R.id.tvItemName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        itemImage = (ImageView) findViewById(R.id.itemImage);

        tvName.setText(item.getItemName());
        tvDescription.setText(item.getDescription());

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvPrice.setText(nf.format(item.getPrice()));

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}