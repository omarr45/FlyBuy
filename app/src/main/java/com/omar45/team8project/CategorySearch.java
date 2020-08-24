package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CategorySearch extends AppCompatActivity {

    private ArrayList<Product> products;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        RecyclerView productsRecyclerView = findViewById(R.id.prodRecView);
        products = new ArrayList<>();
        adapter = new ProductsAdapter();
        adapter.setList(products);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        products.clear();
        String img1 = "https://cdn.shopify.com/s/files/1/0036/4806/1509/products/sp70892169_72de05f6-c006-4ef6-bb83-66f37a045048.jpg?v=1583856427";
        String img2 = "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/5706/5706659_sd.jpg";
        products.add(new Product("Airpods1","4,099 EGP","bla bla","bla bla",img1,img2));
        products.add(new Product("Airpods2","5,099 EGP","bla bla","bla bla",img1,img2));
        products.add(new Product("Airpods3","6,099 EGP","bla bla","bla bla",img2,img1));
        products.add(new Product("Airpods4","7,099 EGP","bla bla","bla bla",img2,img1));
        adapter.notifyDataSetChanged();
    }
}