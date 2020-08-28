package com.omar45.team8project;

import android.os.Bundle;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
 interface ProductDao  {
        @Insert
        Completable insertProduct(Product product);

        @Query("select * From product_table")
        Single<List<Product>> getProduct();

        @Query("SELECT * FROM product_table WHERE category_id = :catID")
        Single<List<Product>> getProductCat(int catID);

        @Query("SELECT * FROM product_table WHERE id = :ID")
        Single<List<Product>> getProductID(int ID);

        @Query("SELECT * FROM product_table WHERE name LIKE '%' || :searchQ || '%' ")
        Single<List<Product>> getProductName(String searchQ);

        @Query("DELETE FROM product_table WHERE id = :prodid")
        Completable delete (int prodid);

}
