package com.example.majid.forurcomfy.Data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by farha on 4/28/2018.
 */

public class FoodMenu implements Parcelable {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("itemId")
    @Expose
    public String itemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("sortPosition")
    @Expose
    public Integer sortPosition;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("image")
    @Expose
    public String image;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSortPosition() {
        return sortPosition;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getItemId() {

        return itemId;
    }

    public String getItemName() {

        return itemName;
    }

    public FoodMenu(String itemId, String itemName,
                    String category,
                    String description, int sortPosition, int price, String image) {



        if (id == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.sortPosition = sortPosition;
        this.price = price;
        this.image = image;
    }
    @Override
    public String toString() {
        return "FoodMenu{" +
                "id='" + id + '\'' +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", sortPosition=" + sortPosition +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeValue(this.sortPosition);
        dest.writeValue(this.price);
        dest.writeString(this.image);
    }

    protected FoodMenu(Parcel in) {
        this.id = in.readString();
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.sortPosition = (Integer) in.readValue(Integer.class.getClassLoader());
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.image = in.readString();
    }

    public static final Parcelable.Creator<FoodMenu> CREATOR = new Parcelable.Creator<FoodMenu>() {
        @Override
        public FoodMenu createFromParcel(Parcel source) {
            return new FoodMenu(source);
        }

        @Override
        public FoodMenu[] newArray(int size) {
            return new FoodMenu[size];
        }
    };
}