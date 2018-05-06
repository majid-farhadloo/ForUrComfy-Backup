package com.example.majid.forurcomfy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majid.forurcomfy.Data.model.FoodMenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    public static final String ITEM_ID_KEY = "item_id_key";
    public static final String ITEM_KEY = "item_key" ;
    private List<FoodMenu> mItems;
    //private List<FoodMenu> mItems2;

    private Context mContext;

    public DataItemAdapter(Context context, List<FoodMenu> items) {
        this.mContext = context;
        this.mItems = items;
    }
    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final FoodMenu item = mItems.get(position);
        InputStream inputStream = null;
        try {
            holder.tvName.setText(item.getItemName());
            String imageFile = item.image;
            inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "You selected " +
                        item.getItemName(), Toast.LENGTH_SHORT).show();
                 String itemId = item.getItemId();
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra(ITEM_ID_KEY,itemId);
                intent.putExtra(ITEM_KEY,item);
                mContext.startActivities(new Intent[]{intent});
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "You long clicked " +
                        item.itemName, Toast.LENGTH_SHORT).show();
                return false;
            }

            public boolean onLongClickListener(View v){
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public ImageView imageView;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.itemNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}