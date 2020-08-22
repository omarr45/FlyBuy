package com.omar45.team8project;

public class Product {


    private String product_name;
    private int product_quantity;
    private double product_price;
    private int product_image;

    public Product(String product_name, int product_quantity, double product_price, int product_image) {
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.product_image = product_image;
    }
    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public int getProduct_image() {
        return product_image;
    }
}
