package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

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
    ProductDatabase productDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDatabase = ProductDatabase.getInstance(this);

//        productDatabase.productDao().delete(1).subscribeOn(Schedulers.computation())
//                .subscribe();
//        productDatabase.productDao().delete(2).subscribeOn(Schedulers.computation())
//                .subscribe();

        ////////////////////////////////////////////////////////////////////////////////////////////
        //Uncomment those two lines only when you finish adding the data ///////////////////////////
//        if(productDatabase.productDao() == null)
//              loadData();

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

    private void loadData() {
        // id, category_id, name, price, desc, specs, img1, img2
        productDatabase.productDao().insertProduct(new Product(
                1, 1, "OPPO Reno 3 Dual SIM - 128GB, 8GB RAM, 4G LTE - Midnight Black",
                5749,
                "OPPO Reno 3 Dual SIM Mobile - 6.4 inches, 128 GB, 8 GB RAM, 4G LTE - Aurora Blue",
                "Brand\tOPPO\nPackage thickness\t17.8 cm\nStorage Capacity\t128 GB\n" +
                        "Package weight\t1150 gm\nNumber Of SIM\tDual SIM\nCellular Network Technology\t4G LTE\n",
                "https://www.rayashop.com/media/product/94c/oppo-reno3-dual-sim-128gb-8gb-ram-4g-lte-black-cba.jpg",
                "https://www.ourshopee.com/ourshopee-img/ourshopee_product_images/820023533web-03.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();
        productDatabase.productDao().insertProduct(new Product(
                2, 2, "My TV",
                5749,
                "OPPO Reno 3 Dual SIM Mobile - 6.4 inches, 128 GB, 8 GB RAM, 4G LTE - Aurora Blue",
                "Brand\tOPPO\nPackage thickness\t17.8 cm\nStorage Capacity\t128 GB\n" +
                        "Package weight\t1150 gm\nNumber Of SIM\tDual SIM\nCellular Network Technology\t4G LTE\n",
                "https://www.elarabygroup.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/t/o/tornado-led-tv-32-inch-hd-with-built-in-receiver-2-hdmi-and-2-usb-inputs-32er9500e-front.jpg",
                "https://www.ourshopee.com/ourshopee-img/ourshopee_product_images/820023533web-03.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();
    }
}