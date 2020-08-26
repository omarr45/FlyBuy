package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

    TextView prodName, prodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

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
                        prodName.setText(products.get(0).getName());
                        prodPrice.setText(String.format("%s EGP", products.get(0).getPrice()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        List<SlideModel> slideModels = new ArrayList<SlideModel>();
        slideModels.add(new SlideModel(R.drawable.airpods1, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.airpods2, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_INSIDE );


        prodDesc = findViewById(R.id.expand_desc);
        prodDesc.setText(getString(R.string.description));

        prodSpecs = findViewById(R.id.expand_specs);
        prodSpecs.setText(getString(R.string.specs));

    }
}