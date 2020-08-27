package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class CategorySearch extends AppCompatActivity implements ProductClickListener {

    Toolbar toolbar;

    /* Categories:
        1. Mobiles
        2. TVs
        3. Perfumes
        4. Gaming
        5. Accessories
        6. Women Wear
        7. Kids Wear
        8. Men Wear
    * */
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        toolbar = findViewById(R.id.toolbarCat);
        setSupportActionBar(toolbar);

        RecyclerView productsRecyclerView = findViewById(R.id.prodRecView);
        adapter = new ProductsAdapter(this, this);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 1);

        switch (category) {
            case 1:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Mobiles");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 2:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("TVs");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 3:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Perfumes");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 4:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Gaming");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 5:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Accessories");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 6:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Women Wear");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 7:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Kids Wear");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
            case 8:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Men Wear");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                break;
        }

        ProductDatabase productDatabase = ProductDatabase.getInstance(this);
        productDatabase.productDao().getProductCat(category)
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

    @Override
    public void onClickProduct(Product product) {
        Intent intent = new Intent(CategorySearch.this, ShowProduct.class);
        intent.putExtra("id", product.getId());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}