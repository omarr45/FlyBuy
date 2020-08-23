package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private int[] images = {R.drawable.airpods1,R.drawable.airpods2,R.drawable.airpods3};
    private CartAdapter adapter;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView=findViewById(R.id.recycler_view);
        CartAdapter adapter= new CartAdapter(ShoppingCart.this,getProductList(),images);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


        checkout=findViewById(R.id.checkout_button);

        //OnClickListeners
        checkout.setOnClickListener(this);

    }


    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for(int i=0;i<3;i++)
            productList.add(new Product("item",i,4520,images[1]));
        return productList;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(ShoppingCart.this, ConfirmDetails.class));
    }

}