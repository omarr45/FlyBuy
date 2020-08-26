package com.omar45.team8project;

import android.content.Context;
import android.os.Bundle;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Product.class,version = 1)
 abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;
    public abstract ProductDao productDao();


        public static synchronized ProductDatabase getInstance(Context context) {

            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ProductDatabase.class, "Product_Database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }


}
