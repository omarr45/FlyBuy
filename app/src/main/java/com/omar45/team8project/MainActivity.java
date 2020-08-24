package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageSlider imageSlider;
    CardView mobiles, tvs, perfumes, gaming, accessories, womenWear, kidsWear, menWear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageSlider
        imageSlider = findViewById(R.id.imageSliderMain);
        List<SlideModel> slideModels = new ArrayList<SlideModel>();
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner4, ScaleTypes.FIT));
        imageSlider.startSliding(2000);
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        mobiles     = findViewById(R.id.mobilesCategory);
        tvs         = findViewById(R.id.tvsCategory);
        perfumes    = findViewById(R.id.perfumesCategory);
        gaming      = findViewById(R.id.gamingCategory);
        accessories = findViewById(R.id.accessoriesCategory);
        womenWear   = findViewById(R.id.women_category);
        kidsWear    = findViewById(R.id.kids_category);
        menWear     = findViewById(R.id.men_category);

        mobiles.    setOnClickListener(this);
        tvs.        setOnClickListener(this);
        perfumes.   setOnClickListener(this);
        gaming.     setOnClickListener(this);
        accessories.setOnClickListener(this);
        womenWear.  setOnClickListener(this);
        kidsWear.   setOnClickListener(this);
        menWear.    setOnClickListener(this);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Intent in = new Intent(MainActivity.this, CategorySearch.class);
                switch (i) {
                    case 0:
                        //mobiles
                        in.putExtra("category", 1);
                    case 1:
                        //kids
                        in.putExtra("category", 7);
                    case 2:
                        //accessories
                        in.putExtra("category", 5);
                    case 3:
                        //perfumes
                        in.putExtra("category", 3);
                    default:
                        in.putExtra("category", 1);
                }
                startActivity(in);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CategorySearch.class);
        switch (view.getId()) {
            case R.id.mobilesCategory:
                intent.putExtra("category", 1);
                break;
            case R.id.tvsCategory:
                intent.putExtra("category", 2);
                break;
            case R.id.perfumesCategory:
                intent.putExtra("category", 3);
                break;
            case R.id.gamingCategory:
                intent.putExtra("category", 4);
                break;
            case R.id.accessoriesCategory:
                intent.putExtra("category", 5);
                break;
            case R.id.women_category:
                intent.putExtra("category", 6);
                break;
            case R.id.kids_category:
                intent.putExtra("category", 7);
                break;
            case R.id.men_category:
                intent.putExtra("category", 8);
                break;
        }
        startActivity(intent);
    }
}