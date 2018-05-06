package com.example.majid.forurcomfy.Data.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by farha on 5/4/2018.
 */

public class ShoppingItem extends Food {
    private List<Food> food = null;
    private String name;
    private String cell;
    private String location;
    private String _id;



//    private String id;
//    private String foodName;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private Date date;

    public ShoppingItem() {

    }

    public ShoppingItem(List<Food> food, String name, String cell, String location, Date date) {

        this.food = food;
        this.name = name;
        this.cell = cell;
        this.location = location;
        this.date = date;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "ShoppingItem{" +
                "id" + _id +
                "food=" + food +
                ", name='" + name + '\'' +
                ", cell='" + cell + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }
}

