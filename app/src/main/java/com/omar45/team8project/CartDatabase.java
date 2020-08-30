package com.omar45.team8project;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Cart.class,version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;

    public abstract CartDao cartDao();

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, "Cart database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
