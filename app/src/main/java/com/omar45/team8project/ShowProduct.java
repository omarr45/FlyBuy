package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

public class ShowProduct extends AppCompatActivity {

    ImageSlider imageSlider;
    ExpandableTextView prodDesc, prodSpecs;

    ProductDatabase productDatabase;

    Toolbar toolbar;
    TextView prodName, prodPrice;

    Button addToCart;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        toolbar = findViewById(R.id.toolbarShowProd);
        setSupportActionBar(toolbar);

        imageSlider = findViewById(R.id.imageSlider);
        addToCart   = findViewById(R.id.add_to_cart);

        prodName = findViewById(R.id.productName);
        prodPrice = findViewById(R.id.productPrice);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 1);
        CartDatabase c_database=CartDatabase.getInstance(this);
        productDatabase = ProductDatabase.getInstance(this);
        c_database.cartDao().getCartItems()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Cart>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onSuccess(List<Cart> cartList) {
                        Log.e("TAG", "onSuccess: "+cartList.size() );
                        for(int i=0;i<cartList.size();i++)
                        {
                            Log.e("TAG", "onSuccess: " + id);
                            Log.e("TAG", "onSuccess: " + (cartList.get(i).getCart_item_id()));
                            if((cartList.get(i).getCart_item_id()) == id){
                                flag =1;
                                break;
                            }
                        }
                        if(flag == 1)
                        {
                            addToCart.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getMessage());

                    }
                });
        productDatabase.productDao().getProductID(id).subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }


                    @Override
                    public void onSuccess(List<Product> products) {
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                            getSupportActionBar().setTitle(products.get(0).getName());
                        }

                        prodName.setText(products.get(0).getName());
                        prodPrice.setText(String.format("%s EGP", products.get(0).getPrice()));

                        List<SlideModel> slideModels = new ArrayList<SlideModel>();
                        slideModels.add(new SlideModel(products.get(0).getImg1(), ScaleTypes.CENTER_INSIDE));
                        slideModels.add(new SlideModel(products.get(0).getImg2(), ScaleTypes.CENTER_INSIDE));
                        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_INSIDE );

                        prodDesc = findViewById(R.id.expand_desc);
                        prodDesc.setText(products.get(0).getDescription());

                        prodSpecs = findViewById(R.id.expand_specs);
                        prodSpecs.setText(products.get(0).getSpecifications());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });



        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ShowProduct.this, ShoppingCart.class);
                in.putExtra("id", id);
                Log.e("TAG", "onClick: "+id );
                startActivity(in);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}