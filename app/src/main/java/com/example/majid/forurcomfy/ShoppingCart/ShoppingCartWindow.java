package com.example.majid.forurcomfy.ShoppingCart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majid.forurcomfy.CheckoutActivity;
import com.example.majid.forurcomfy.Common.Current;
import com.example.majid.forurcomfy.Data.model.OrderProcess;
import com.example.majid.forurcomfy.Data.model.ShoppingItem;
import com.example.majid.forurcomfy.Data.model.SqliteHelper;
import com.example.majid.forurcomfy.R;
import com.example.majid.forurcomfy.Remote.APIService;
import com.example.majid.forurcomfy.Remote.ApiUtlis;
import com.example.majid.forurcomfy.model.Request;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartWindow extends AppCompatActivity {

    private final String TAG = ShoppingCartWindow.class.getSimpleName();

    Boolean isCartEmpty = true;
    TextView priceView;
    SqliteHelper dbhelper;
    int totalAmount = 0;
    ArrayList<ShoppingItem> items;
    ListView cartList;
    String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_window);
        dbhelper = new SqliteHelper(getApplicationContext(), "ShoppingCart.db", null, 1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM SHOPPING_CART", null);
        startManagingCursor(cursor);

        MyListAdapter listAdapter = null;
        listAdapter = new MyListAdapter(this, cursor, 0);

        priceView = (TextView) findViewById(R.id.totalPriceCheckout);
        cartList = (ListView) findViewById(R.id.shoppingCartList);
        cartList.setAdapter(listAdapter);

        int total = dbhelper.getTotalPrice();

        // Create the list
//        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
//        listViewCatalog.setAdapter(new ShoppingCartAdapter(items, getLayoutInflater(), false));
//
//        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
//                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
//                startActivity(productDetailsIntent);
//            }
//        });


        priceView.setText(Integer.toString(total));

        (findViewById(R.id.returnToPrevPage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        (findViewById(R.id.checkOut)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create new Request
                showAlertDialog();

            }
        });

        (findViewById(R.id.clearCart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhelper.clear();
                MyListAdapter listAdapter = null;
                cartList.setAdapter(listAdapter);
                priceView.setText("0");
                Snackbar.make(findViewById(R.id.shoppingCartWindowLayout),
                        "Cleared!",
                        Snackbar.LENGTH_SHORT).show();
                //clearCart();
            }
        });
    }

    class MyItem {
        MyItem(int itemName) {
            itemName = itemName;
        }

        int itemName;
    }

    class MyListAdapter extends CursorAdapter {
        private LayoutInflater cursorInflater;

        public MyListAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            cursorInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return cursorInflater.inflate(R.layout.cart_list_view, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView textView = (TextView) view.findViewById(R.id.text1);
            TextView et2 = (TextView) view.findViewById(R.id.text2);

            String name = cursor.getString(cursor.getColumnIndex("itemName"));
            int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));

            textView.setText(name);
            et2.setText(Integer.toString(quantity));
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShoppingCartWindow.this);
        alertDialog.setTitle("For last Step");
        alertDialog.setMessage("Please Enter Your address if you would like your food to be " +
                "delivered");

        final EditText edtAddress = new EditText(ShoppingCartWindow.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //Creat new Request
                Request req = new Request(Current.currentUser.getCell(),
                        Current.currentUser.getfirstname(),
                        Current.currentUser.getlastname(), edtAddress.getText().toString(),
                        priceView.getText().toString(), items);
                // sending them to server


                APIService mAPIService = ApiUtlis.getAPIService();


                mAPIService.request(items, Current.currentUser.getCell(),
                        edtAddress.getText().toString(), Current.currentUser).enqueue(new Callback<OrderProcess>() {
                    @Override
                    public void onResponse(Call<OrderProcess> call, Response<OrderProcess> response) {
                        if (response.isSuccessful()) {
                            Log.d("+++", "Success");
                            msg = response.body().getMessage();
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

//                    mRecyclerView.set(mItemAdapter);
                        } else {
                            Log.d("+++", "Fail");
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderProcess> call, Throwable t) {
                        Log.d("+++", "Fail");
                    }
                });
                Intent intent =  new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra(CheckoutActivity.PRICE, priceView.getText());
                //Go to payment Activity
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));
                Intent intent =  new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra(CheckoutActivity.PRICE, priceView.getText());
                //Go to payment Activity
                startActivity(intent);
            }
        });

        alertDialog.show();

    }


}
