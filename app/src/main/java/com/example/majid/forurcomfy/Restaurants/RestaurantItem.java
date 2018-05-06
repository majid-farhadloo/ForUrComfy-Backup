package com.example.majid.forurcomfy.Restaurants;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class RestaurantItem implements Parcelable {

    private String itemId;
    private String itemName;
    private String image;

    public RestaurantItem() {
    }

    public RestaurantItem(String itemId, String itemName, String image) {
        if (itemId == null){
            itemId = UUID.randomUUID().toString();
        }
        this.itemId = itemId;
        this.itemName = itemName;
        this.image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "DataItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.image);
    }

    protected RestaurantItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.image = in.readString();
    }

    public static final Creator<RestaurantItem> CREATOR = new Creator<RestaurantItem>() {
        @Override
        public RestaurantItem createFromParcel(Parcel source) {
            return new RestaurantItem(source);
        }

        @Override
        public RestaurantItem[] newArray(int size) {
            return new RestaurantItem[size];
        }
    };
}
