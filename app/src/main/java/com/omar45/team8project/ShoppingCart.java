package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    Toolbar toolbar;
    private CartAdapter adapter;
    boolean go = false;

    SharedPreferences sharedPreferences;
    public static ArrayList<String> idList= new ArrayList<>();
    Button checkout;
    float total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        //  productList

        toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cart");
        }

        Intent in = getIntent();
        final int id = in.getIntExtra("id", 20);

        RecyclerView recyclerView = findViewById(R.id.shopping_recycler_view);
        adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        final ProductDatabase database = ProductDatabase.getInstance(this);

        database.productDao().getProductID(id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Product> products) {

                        ArrayList<String> temp = null;
                        try {
                            temp = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ID" , ObjectSerializer.serialize(new ArrayList<>())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(temp.isEmpty()) {
                            idList.add(Integer.toString(products.get(0).getId()));
                            try {
                                sharedPreferences.edit().putString("ID", ObjectSerializer.serialize(idList)).apply();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            idList = temp;
                            idList.add(Integer.toString(products.get(0).getId()));
                            try {
                                sharedPreferences.edit().putString("ID", ObjectSerializer.serialize(idList)).apply();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.setList(sharedPreferences);
                        adapter.notifyDataSetChanged();

                    }


                    @Override
                    public void onError(Throwable e) {

                    }
                });



        checkout=findViewById(R.id.checkout_button);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}