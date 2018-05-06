package com.example.majid.forurcomfy.ShoppingCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.majid.forurcomfy.Data.model.Food;
import com.example.majid.forurcomfy.Data.model.ShoppingItem;
import com.example.majid.forurcomfy.R;

import java.util.List;


public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {

    Context context;

    public ShoppingListAdapter(Context context, List<ShoppingItem> items){
        super(context, 0, items);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.shopping_item, parent, false
            );
        }

        ShoppingItem currentItem = getItem(position);
        // adding new current food
        Food currentFood = getItem(position);

//        ImageView img = (ImageView) listItemView.findViewById(R.id.itemIcon);
//        Picasso.with(getContext())
//                .load(context.getApplicationContext().getString(R.string.ip)
//                        + String.valueOf(currentItem.getProductID())
//                        + ".jpg")
//                .fit().centerCrop()
//                .into(img);

        TextView name = (TextView) listItemView.findViewById(R.id.itemName);
        name.setText(currentFood.getFoodName());

//        TextView description = (TextView) listItemView.findViewById(R.id.itemDescription);
//        description.setText(currentItem.getDescription());

        TextView cost = (TextView) listItemView.findViewById(R.id.itemPrice);
        cost.setText((int) currentFood.getPrice());

        return listItemView;
    }
}
//.resizeDimen(R.dimen.forImage, R.dimen.forImage)