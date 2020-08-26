package com.omar45.team8project;

import android.os.Bundle;
import android.database.Observable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
 interface ProductDao  {
        @Insert
        Completable insertProduct(Product product);

        @Query("select * From product_table")
        Single<List<Product>> getProduct();



}
