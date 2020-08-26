package com.omar45.team8project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int c_id;
    private String name;
    private String price;
    private String description;
    private String specifications;
    private String img1;
    private String img2;

    public Product() { }

    public Product(String name, String price, String description, String specifications, String img1) {
        //this.c_id=c_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.specifications = specifications;
        this.img1 = img1;
        //this.img2 = img2;
    }

    public void setC_id(int c_id) { this.c_id = c_id; }

    public int getC_id() { return c_id; }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }
}
