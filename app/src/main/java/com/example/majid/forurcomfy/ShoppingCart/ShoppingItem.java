package com.example.majid.forurcomfy.ShoppingCart;

import java.sql.Date;

/**
 * Created by farha on 5/4/2018.
 */

public class ShoppingItem {
    private String id;
    private String foodName;
    private String cell;
    private String location;
    private int price, quantity;
    private Date date;

//    private String image;

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public ShoppingItem(String foodName, String cell, String location, int price, int quantity, Date date) {
        this.foodName = foodName;
        this.cell = cell;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "foodName='" + foodName + '\'' +
                ", cell='" + cell + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

