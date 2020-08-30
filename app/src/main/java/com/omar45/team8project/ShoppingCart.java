package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.facebook.appevents.suggestedevents.ViewOnClickListener;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ShoppingCart extends AppCompatActivity {
    Toolbar toolbar;
  //  CartDatabase cartDatabase;
   public static ProductDatabase productDatabase;
   //List<ProductDatabase>productDatabases;
   private CartAdapter adapter;
    Button checkout;
    Context context;
    public static List<Product>productList;
    public List<Cart>pCart;
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
        final ProductDatabase p_database=ProductDatabase.getInstance(this);

        if (id!=50) {
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
                            Log.i("product", productList.size() + "");

                            c_database.cartDao().addToCart(new Cart(productList.get(0).getId(), productList.get(0).getName()
                                    , productList.get(0).getPrice(), productList.get(0).getImg1(), 1))
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            Log.e("TAG", "onSubscribe: ");
                                        }

                                        @Override
                                        public void onComplete() {
                                            Log.e("TAG", "onComplete: ");

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.e("TAG", "onError: " + e.getMessage());
                                        }
                                    });

                            //adapter.addToCart(productList);
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }

        c_database.cartDao().getCartItems()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Cart>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onSuccess(List<Cart> cartList) {
                        Log.e("TAG", "onSuccess: " + cartList.size());
                        adapter.setList(cartList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());

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
