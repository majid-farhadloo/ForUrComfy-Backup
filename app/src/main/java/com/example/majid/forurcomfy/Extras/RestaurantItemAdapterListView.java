//package com.example.majid.forurcomfy.Extras;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.majid.forurcomfy.R;
//import com.example.majid.forurcomfy.Restaurants.RestaurantItem;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class RestaurantItemAdapterListView extends ArrayAdapter<RestaurantItem> {
//
//    List<RestaurantItem> mDataItems;
//    LayoutInflater mInflater;
//
//    public RestaurantItemAdapterListView(Context context, List<RestaurantItem> objects) {
//        super(context, R.layout.restaurant_item, objects);
//
//        mDataItems = objects;
//        mInflater = LayoutInflater.from(context);
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.list_item, parent, false);
//        }
//
//        TextView tvName = (TextView) convertView.findViewById(R.id.itemNameText);
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
//
//        RestaurantItem item = mDataItems.get(position);
//
//        tvName.setText(item.getItemName());
////        imageView.setImageResource(R.drawable.apple_pie);
//
//        InputStream inputStream = null;
//        try {
//            String imageFile = item.getImage();
//           // File file = new File(getFilesDir(), assetsRestaurants);
//            inputStream = getContext().getAssets().open("asset/" + "assetsRestaurants");
//            Drawable d = Drawable.createFromStream(inputStream, null);
//            imageView.setImageDrawable(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return convertView;
//    }
//}
