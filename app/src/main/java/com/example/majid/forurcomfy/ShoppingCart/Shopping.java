package com.example.majid.forurcomfy.ShoppingCart;

public class Shopping {
    private String foodName;
    private String price;
    private String quantity;
    private String totalprice;
    // Set Methods

    public Shopping (String foodName, String price, String quantity, String totalprice) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    // Get Methods

    public String getFoodName() { return this.foodName; }

    public String getPrice() { return this.price; }

    public String getQuantity() { return  this.quantity; }

    public String getTotalprice() { return  this.totalprice; }


}
