package com.example.majid.forurcomfy.ShoppingCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majid.forurcomfy.Data.model.Food;
import com.example.majid.forurcomfy.Data.model.ShoppingItem;
import com.example.majid.forurcomfy.R;
import com.example.majid.forurcomfy.Remote.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class newShoppingCartAdapter extends ArrayAdapter {
    Context context;
    List<ShoppingItem> items = new ArrayList<>();
    List<Food> foods;
    private APIService mAPIService;

    public newShoppingCartAdapter(Context context, List<ShoppingItem> items){
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }
    public newShoppingCartAdapter(Context context, List<ShoppingItem> items,APIService mAPIService){
        super(context, 0, items);
        this.context = context;
        this.items = items;
        this.mAPIService=mAPIService;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_d_r, parent, false
            );
        }

        LinearLayout ll = listItemView.findViewById(R.id.individialListItemForCartAdapter);
        final View finalListItemView = listItemView;
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAPIService.claimed(items.get(position).get_id()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("+++", "Success");
                        String msg = response.body();
                        Toast.makeText(finalListItemView.getContext(),msg,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

        ShoppingItem currentItem = (ShoppingItem) getItem(position);
        //Food currentFood = (Food) getItem(position);
        List<Food> currentFoods = currentItem.getFood();
        ((TextView) listItemView.findViewById(R.id.name))
                .setText( currentItem.getName());

        ((TextView) listItemView.findViewById(R.id.id))
                .setText( currentItem.get_id());

        ((TextView) listItemView.findViewById(R.id.location))
                .setText( currentItem.getLocation());

        ((TextView) listItemView.findViewById(R.id.cell))
                .setText( currentItem.getCell());

        ((TextView) listItemView.findViewById(R.id.quantity))
                .setText(currentFoods.size());


//        String x = "x " + String.valueOf(currentItem.get_id());
//        ((TextView) listItemView.findViewById(R.id.id))
//                .setText(x);

//        int itemPrice=0;
//        try{
//            itemPrice = Integer.valueOf(NumberFormat.getCurrencyInstance()
//                    .parse(String.valueOf(currentFood.getPrice()))
//                    .toString());
//        } catch (ParseException e){
//            e.printStackTrace();
//        }
//        ((TextView) listItemView.findViewById(R.id.cartItemPrice))
//                .setText(NumberFormat.getCurrencyInstance().format(itemPrice));
//
//        ((TextView) listItemView.findViewById(R.id.cartItemTotal))
//                .setText(NumberFormat.getCurrencyInstance().format(itemPrice * currentFood.getQuantity()));

        return listItemView;
    }
}
