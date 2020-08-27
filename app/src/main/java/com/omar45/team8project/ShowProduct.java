package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        toolbar = findViewById(R.id.toolbarShowProd);
        setSupportActionBar(toolbar);

        imageSlider = findViewById(R.id.imageSlider);

        prodName = findViewById(R.id.productName);
        prodPrice = findViewById(R.id.productPrice);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);

        productDatabase = ProductDatabase.getInstance(this);
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

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}