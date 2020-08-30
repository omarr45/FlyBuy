package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.facebook.appevents.suggestedevents.ViewOnClickListener;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {
    Toolbar toolbar;
  //  CartDatabase cartDatabase;
   public static ProductDatabase productDatabase;
   //List<ProductDatabase>productDatabases;
   private CartAdapter adapter;
    Button checkout;
    Context context;
    public static List<Product>productList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

         productList = new ArrayList<>();

        //cartDatabase=CartDatabase.getInstance(this);
        productDatabase=ProductDatabase.getInstance(this);
        checkout = findViewById(R.id.checkout_button);

        //  productList

        toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cart");
        }

        Intent in = getIntent();
        final int id = in.getIntExtra("id", 50);
         recyclerView = findViewById(R.id.shopping_recycler_view);
        adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        final CartDatabase c_database = CartDatabase.getInstance(this);
         ProductDatabase p_database=ProductDatabase.getInstance(this);

            p_database.productDao().getProductID(id)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Product>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<Product> products) {
                            productList.add(products.get(0));
                             adapter.setList(productList);
                            //adapter.addToCart(productList);
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ShoppingCart.this, ConfirmationOrder.class));

                }
            });
        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
