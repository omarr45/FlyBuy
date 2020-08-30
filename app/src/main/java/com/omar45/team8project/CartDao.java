package com.omar45.team8project;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface CartDao {
    @Insert
    Completable addToCart(Cart cart);

    @Query(" select * from Cart")
    Single<List<Cart>> getCartItems();

    @Query("SELECT * FROM cart WHERE cart_item_id = :ID")
    Single<List<Cart>> getCartID(int ID);

//    @Query("Delete From Cart")
//    Void deleteAll();
}
