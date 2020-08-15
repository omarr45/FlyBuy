package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

public class ShowProduct extends AppCompatActivity {

    ImageSlider imageSlider;
    ExpandableTextView expDesc, expSpecs;

    /*What matters ?
        1. productName
        2. productPrice
        3. expDesc.setText
        4. expSpecs.setText
        5.slideModels.add each photo

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        imageSlider = findViewById(R.id.imageSlider);

        List<SlideModel> slideModels = new ArrayList<SlideModel>();
        slideModels.add(new SlideModel(R.drawable.airpods1, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.airpods2, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.airpods3, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_INSIDE );


        expDesc= findViewById(R.id.expand_desc);
        expDesc.setText(getString(R.string.description));

        expSpecs= findViewById(R.id.expand_specs);
        expSpecs.setText(getString(R.string.specs));

    }
}