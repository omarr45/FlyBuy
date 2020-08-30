package com.omar45.team8project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class Cart {
    @PrimaryKey
    private int cart_item_id;
    private String cart_item_name;
    private float cart_item_price;
    private String cart_item_img;
    private int cart_item_quantity;

    public Cart() { }

    public Cart(int id, String name, float price, String img,int quantity) {
        this.cart_item_id = id;
        this.cart_item_name = name;
        this.cart_item_price = price;
        this.cart_item_img = img;
       this.cart_item_quantity=quantity;
    }

    public int getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(int cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public String getCart_item_name() {
        return cart_item_name;
    }

    public void setCart_item_name(String cart_item_name) {
        this.cart_item_name = cart_item_name;
    }

    public float getCart_item_price() {
        return cart_item_price;
    }

    public void setCart_item_price(float cart_item_price) {
        this.cart_item_price = cart_item_price;
    }

    public String getCart_item_img() {
        return cart_item_img;
    }

    public void setCart_item_img(String cart_item_img) {
        this.cart_item_img = cart_item_img;
    }

    public int getCart_item_quantity() {
        return cart_item_quantity;
    }

    public void setCart_item_quantity(int cart_item_quantity) {
        this.cart_item_quantity = cart_item_quantity;
    }
}
