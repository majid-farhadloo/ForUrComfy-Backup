package com.example.majid.forurcomfy.Data.model;

public class Food {

    private String foodName;
    private double price;
    private int quantity;


    public Food() {
    }

    /**
     *
     * @param price
     * @param quantity
     * @param foodName
     */
    public Food(String foodName, double price, int quantity) {
        super();
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("foodName", foodName).append("price", price).append("quantity", quantity).toString();
//    }

}