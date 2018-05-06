package com.example.majid.forurcomfy.model;

import com.example.majid.forurcomfy.Data.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farha on 5/4/2018.
 */

public class Request {
    private String phone;
    private String address;
    private String name;
    private String total;
    private List<ShoppingItem> foods;

    public Request(String cell, String getfirstname, String getlastname, String s, String toString, ArrayList<ShoppingItem> items) {
    }

    public Request(String phone, String address, String name, String total, List<ShoppingItem> foods) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.total = total;
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ShoppingItem> getFoods() {
        return foods;
    }

    public void setFoods(List<ShoppingItem> foods) {
        this.foods = foods;
    }

}
