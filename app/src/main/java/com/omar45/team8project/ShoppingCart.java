package com.omar45.team8project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class ShoppingCart extends AppCompatActivity {


    public static ProductDatabase productDatabase;
    private CartAdapter adapter;
    private RecyclerView recyclerView;
    public float totalPrice;
    Toolbar toolbar;
    TextView _total;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cart");
        }

        _total       = findViewById(R.id.total);
        checkout     = findViewById(R.id.checkout_button);
        recyclerView = findViewById(R.id.shopping_recycler_view);

        totalPrice  = (float) 0.0;

        adapter     = new CartAdapter(this);

        productDatabase=ProductDatabase.getInstance(this);

        Intent in = getIntent();
        final int id = in.getIntExtra("id", 50);
        final int quantity=in.getIntExtra("quantity",1);

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
                            Product p = products.get(0);

                            c_database.cartDao().addToCart(new Cart(id, p.getName(), p.getPrice(), p.getImg1(), quantity))
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                        }

                                        @Override
                                        public void onComplete() {
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                        }
                                    });
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            Toast.makeText(this, "Item was added successfully", Toast.LENGTH_LONG).show();
            finish();
        }

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCart.this, ConfirmationOrder.class));
            }
        });

        c_database.cartDao().getCartItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<Cart>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(final List<Cart> cartList) {

                        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                                    @Override
                                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                                        return false;
                                    }

                                    @Override
                                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                                        cartList.remove(viewHolder.getAdapterPosition());
                                        adapter.notifyDataSetChanged();
                                    }
                                };

                        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

                        adapter.setList(cartList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                        for(int i=0; i<cartList.size(); i++) {
                            totalPrice += (1.0 * (cartList.get(i).getCart_item_price() * cartList.get(i).getCart_item_quantity()));
                            Log.i("total",String.valueOf(totalPrice));
                        }

                        _total.setText(totalPrice + " EGP");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
