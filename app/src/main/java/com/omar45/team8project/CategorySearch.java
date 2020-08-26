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

import java.util.ArrayList;
import java.util.List;

public class CategorySearch extends AppCompatActivity implements ProductClickListener {

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

        RecyclerView productsRecyclerView = findViewById(R.id.prodRecView);
        adapter = new ProductsAdapter(this, this);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 1);

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
}