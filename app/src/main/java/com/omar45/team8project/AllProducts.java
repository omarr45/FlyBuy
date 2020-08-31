package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AllProducts extends AppCompatActivity implements ProductClickListener{

    RecyclerView productsRecyclerView;
    private ProductsAdapter adapter;
    ProductDatabase productDatabase;

    EditText inputText;
    Toolbar toolbar;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        toolbar = findViewById(R.id.toolbarAllprod);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("All Products");
        }

        inputText   = findViewById(R.id.input);
        search      = findViewById(R.id.searchButAll);

        productsRecyclerView = findViewById(R.id.allProdRecView);
        adapter = new ProductsAdapter(this, this);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setAdapter(adapter);

        productDatabase = ProductDatabase.getInstance(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputText == null || inputText.getText().toString().equals("")) {
                    productDatabase.productDao().getProducts()
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<List<Product>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<Product> products) {
                                    adapter.setList(products);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                }else {
                    productDatabase.productDao().getProductName(inputText.getText().toString())
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<List<Product>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<Product> products) {
                                    adapter.setList(products);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                }
            }
        });


    }

    @Override
    public void onClickProduct(Product product) {
        Intent intent = new Intent(AllProducts.this, ShowProduct.class);
        intent.putExtra("id", product.getId());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}