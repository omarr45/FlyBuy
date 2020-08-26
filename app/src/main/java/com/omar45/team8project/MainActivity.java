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
        if(productDatabase.productDao() == null)
              loadData();

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




        // Gaming '4'
        productDatabase.productDao().insertProduct(new Product(
                16, 4, "Nintendo Switch Lite - Turquoise",
                6091,
                "•\tExternal Product ID : 9318113992084\n" +
                        "•\tPlatform : Nintendo Switch\n" +
                        "•\tExternal Product ID Type : UPC-A\n" +
                        "•\tHard Disk Capacity : 32 GB\n" +
                        "•\tBrand : Nintendo\n",
                "Brand\n" +
                        "Nintendo\n" +
                        "Platform\n" +
                        "Nintendo Switch\n" +
                        "Package thickness\n" +
                        "23 centimeters\n" +
                        "Package weight in KGs\n" +
                        "584 grams\n" +
                        "UPC-A\n" +
                        "045496882266\n" +
                        "Expirable\n" +
                        "External Product ID Type\n" +
                        "UPC-A\n" +
                        "Package height\n" +
                        "8.2 centimeters\n" +
                        "Package Width\n" +
                        "11.2 centimeters\n" +
                        "External Product ID\n" +
                        "9318113992084\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725060535207\n" +
                        "\n",
                "https://cf2.s3.souqcdn.com/item/2019/10/18/77/44/17/60/item_XL_77441760_6a0e377d35401.jpg",
                "https://cf1.s3.souqcdn.com/item/2019/10/18/77/44/17/60/item_XL_77441760_e4ec9ee008d66.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                17, 4, "FORZA HORIZON 4 FOR XBOX ONE",
                1099,
                "In Forza Horizon 4 you explore beautiful scenery, collect over 450 cars, and become a Horizon Superstar in historic Britain. One of the best Racing game series ever .\n" +
                        "\n" +
                        "•\tThe Forza Horizon 4 Standard Edition includes: Forza Horizon 4 game disc\n" +
                        "•\tSEASONS CHANGE EVERYTHING. For the first time in the racing and driving genre, experience dynamic seasons in a shared open-world. Explore beautiful scenery, collect over 450 cars, and become a Horizon Superstar in historic Britain. (Some cars provided by online update.)\n" +
                        "•\tBritain Like You've Never Seen it. Discover lakes, valleys, castles, and breathtaking scenery all in spectacular native 4K and HDR on Xbox One X and Windows 10\n" +
                        "•\tMaster Driving in All Seasons. Explore a changing world through dry, wet, muddy, snowy, and icy conditions\n" +
                        "•\tNew Content Every Week. Every week, a new season change brings new themed content, gameplay, challenges, and reward.\n" +
                        "\n",
                "Brand\n" +
                        "Microsoft\n" +
                        "Platform\n" +
                        "Xbox One\n" +
                        "Package thickness\n" +
                        "17 centimeters\n" +
                        "Package weight in KGs\n" +
                        "80 grams\n" +
                        "Region\n" +
                        "PAL Region\n" +
                        "Game Name \n" +
                        "FORZA HORIZON 4\n" +
                        "Format_en\n" +
                        "dvd_and_blu_ray\n" +
                        "Package height\n" +
                        "1.6 centimeters\n" +
                        "Package Width\n" +
                        "13.4 centimeters\n" +
                        "Item EAN\n" +
                        "2724666484407\n",
                "https://cf2.s3.souqcdn.com/item/2018/10/01/38/64/47/36/item_XL_38644736_151892065.jpg",
                "https://cf2.s3.souqcdn.com/item/2018/10/01/38/64/47/36/item_XL_38644736_151892065.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                18, 4, "Sony Playstation 4 500GB with Fifa 20",
                6477,
                "Sony Playstation 4 500GB with Fifa 20",
                "Brand\n" +
                        "Sony\n" +
                        "Platform\n" +
                        "PlayStation 4\n" +
                        "Package thickness\n" +
                        "43 centimeters\n" +
                        "Package weight in KGs\n" +
                        "3460 grams\n" +
                        "UPC-A\n" +
                        "711719978008\n" +
                        "Expirable\n" +
                        "External Product ID Type\n" +
                        "UPC-A\n" +
                        "Package height\n" +
                        "9.4 centimeters\n" +
                        "Package Width\n" +
                        "34.6 centimeters\n" +
                        "External Product ID\n" +
                        "711719978008\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725012995240\n" +
                        "\n",
                "https://cf2.s3.souqcdn.com/item/2019/09/30/72/69/84/56/item_XL_72698456_9ca284386fb40.jpg",
                "https://cf2.s3.souqcdn.com/item/2019/09/30/72/69/84/56/item_XL_72698456_9ca284386fb40.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                19, 4, "Microsoft Xbox One S 1 TB - White",
                6352,
                "Experience a delightful gaming experience with the Microsoft Xbox One S 1TB." +
                        " It is designed aesthetically in white color and black accents that give your table space a charming look. ",
                "Brand\n" +
                        "Microsoft\n" +
                        "Platform\n" +
                        "Xbox One S\n" +
                        "Package thickness\n" +
                        "42.8 centimeters\n" +
                        "Package weight in KGs\n" +
                        "4122 grams\n" +
                        "Online capabilities\n" +
                        "Online Registration\n" +
                        "EAN-13\n" +
                        "889842214079\n" +
                        "Expirable\n" +
                        "Package height\n" +
                        "10.8 centimeters\n" +
                        "Package Width\n" +
                        "31.2 centimeters\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2724455627787\n" +
                        "\n",
                "https://cf2.s3.souqcdn.com/item/2017/06/10/23/04/70/45/item_XL_23047045_32423183.jpg",
                "https://cf5.s3.souqcdn.com/item/2017/06/10/23/04/70/45/item_XL_23047045_32423184.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                20, 4, "Sony PlayStation 4 Pro Console 1TB with Marvel's Spider Man",
                9930,
                "The Sony PS4 Pro lets you explore the vivid gaming world like never before." +
                        " The Sony PlayStation 4 Pro takes you on an awe inspiring journey with a heightened visual and breath-taking audio experience. ",
                "Brand\n" +
                        "Sony\n" +
                        "Platform\n" +
                        "PlayStation 4 pro\n" +
                        "Package thickness\n" +
                        "20.8 centimeters\n" +
                        "Package weight in KGs\n" +
                        "438 grams\n" +
                        "Online capabilities\n" +
                        "Online Registration\n" +
                        "Package height\n" +
                        "6.6 centimeters\n" +
                        "Package Width\n" +
                        "17 centimeters\n" +
                        "Item EAN\n" +
                        "2724665810276\n" +
                        "\n",
                "https://cf4.s3.souqcdn.com/item/2018/09/16/38/57/98/02/item_XL_38579802_150490263.jpg",
                "https://cf4.s3.souqcdn.com/item/2018/09/16/38/57/98/02/item_XL_38579802_150490267.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();




        //	Tech accessories '5'

        productDatabase.productDao().insertProduct(new Product(
                21, 5, "Apple AirPods Pro, White - MWP22ZA/A",
                4099,
                "Block the outside world and dive deep into your music with the Apple AirPods Pro." +
                        " They sport a unique white color that is certain to turn many heads. ",
                "Brand\n" +
                        "Apple\n" +
                        "Manufacturer Number\n" +
                        "MWP22ZA/A\n" +
                        "Package thickness\n" +
                        "16 centimeters\n" +
                        "Package weight in KGs\n" +
                        "146 grams\n" +
                        "Model Number\n" +
                        "MWP22ZA/A\n" +
                        "Connectivity\n" +
                        "Wireless\n" +
                        "Compatible with\n" +
                        "Mobile Phones\n",
                "https://cf1.s3.souqcdn.com/item/2020/07/29/13/16/03/45/8/item_XXL_131603458_1c810b5153fef.jpg",
                "https://cf1.s3.souqcdn.com/item/2020/07/29/13/16/03/45/8/item_XXL_131603458_65ed8637166ed.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                22, 5, "Anker A1230 PowerCore Power Bank, 10000 mAh - Black",
                594,
                "•\tBrand: Anker\n" +
                        "•\tModel: A1230\n" +
                        "•\tType: Wired Power Bank\n" +
                        "•\tColor: Black\n" +
                        "•\tCapacity: 10000 mAh\n",
                "Brand\n" +
                        "Anker\n" +
                        "Manufacturer Number\n" +
                        "A1230\n" +
                        "Color\n" +
                        "Black\n" +
                        "Package thickness\n" +
                        "17.6 centimeters\n" +
                        "Depth\n" +
                        "172 millimeters\n" +
                        "Package weight in KGs\n" +
                        "320 grams\n" +
                        "Height\n" +
                        "38 millimeters\n" +
                        "Width\n" +
                        "142 millimeters\n" +
                        "Product weight\n" +
                        "320 grams\n" +
                        "Type\n" +
                        "Wired Power Bank\n",
                "https://cf5.s3.souqcdn.com/item/2018/04/23/33/48/85/65/item_XXL_33488565_131335138.jpg",
                "https://cf5.s3.souqcdn.com/item/2018/04/23/33/48/85/65/item_XXL_33488565_131335138.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                23 , 5, "Apple Watch Series 4 - 44mm Space Gray Aluminum Case with Black Sport Band, GPS, watchOS 5",
                11500,
                "The Apple Series 4 smartwatch runs on a powerful S4 dual-core processor that delivers fast and efficient performance.",
                "Brand\n" +
                        "Apple\n" +
                        "Manufacturer Number\n" +
                        "2724665530914.0\n" +
                        "Monitor Type\n" +
                        "LTPO OLED Retina\n" +
                        "RAM Size\n" +
                        "1\n" +
                        "Package thickness\n" +
                        "29.6 centimeters\n" +
                        "Battery type\n" +
                        "Built-in rechargeable lithium-ion battery\n" +
                        "Package weight in KGs\n" +
                        "500 grams\n" +
                        "Wireless Standard\n" +
                        "802.11b/g/n 2.4GHz\n" +
                        "\n",
                "https://cf3.s3.souqcdn.com/item/2018/09/14/38/55/30/38/item_XXL_38553038_150336463.jpg",
                "https://cf1.s3.souqcdn.com/item/2018/09/14/38/55/30/38/item_XXL_38553038_150336469.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                24, 5, "Xiaomi Mi Band 4 Smart Bluetooth Fitness Bracelet Chinese Version",
                621,
                "•\tType : Fitness Trackers\n" +
                        "•\tBrand : Xiaomi\n" +
                        "•\tDisplay Type : Digital\n" +
                        "•\tCompatible with : Andriod & iOS\n" +
                        "\n",
                "Brand\n" +
                        "Xiaomi\n" +
                        "Package thickness\n" +
                        "17.6 centimeters\n" +
                        "Package weight in KGs\n" +
                        "160 grams\n" +
                        "Display Type\n" +
                        "Digital\n" +
                        "Type\n" +
                        "Fitness Trackers\n" +
                        "Compatible with\n" +
                        "Andriod & iOS\n",
                "https://cf3.s3.souqcdn.com/item/2019/09/09/68/26/94/46/item_XXL_68269446_aebc5c5b72818.jpg",
                "https://cf3.s3.souqcdn.com/item/2019/09/09/68/26/94/46/item_XXL_68269446_d525d91655b74.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                25, 5, "Lenovo X18 True Wireless Bluetooth 5.0 Waterproof Earbuds - Black",
                517,
                "•\tBrand: Lenovo\n" +
                        "•\tModel: X18\n" +
                        "•\tColor: Black\n" +
                        "•\tBluetooth version: V5.0\n" +
                        "•\tBluetooth distance: ≤20 meters\n" +
                        "•\tMicrophone: -42 Decibels\n" +
                        "\n",
                "Brand\n" +
                        "Lenovo\n" +
                        "Manufacturer Number\n" +
                        "X18\n" +
                        "Model Number\n" +
                        "X18\n" +
                        "Connectivity\n" +
                        "Wireless\n" +
                        "Compatible with\n" +
                        "Mobile Phones\n" +
                        "\n",
                "https://cf1.s3.souqcdn.com/item/2020/08/16/13/16/77/48/7/item_XXL_131677487_2671c204c0b9e.jpg",
                "https://cf3.s3.souqcdn.com/item/2020/08/16/13/16/77/48/7/item_XXL_131677487_7468685076cde.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        //Women Wear  '6'

        productDatabase.productDao().insertProduct(new Product(
                26, 6, "Aeropostale Sleeveless High-Neck Ribbed Skater Mini Dress for Women - Grey",
                1499,
                "•\tBrand: Aeropostale\n" +
                        "•\tType: Dress\n" +
                        "•\tMaterial: 57% Cotton and 43% Lyocell\n" +
                        "•\tColor: Grey\n" +
                        "•\tDress Length: Mini\n" +
                        "•\tHigh neck\n",
                "Brand\n" +
                        "Aeropostale\n" +
                        "Package thickness\n" +
                        "31.4 centimeters\n" +
                        "Package weight in KGs\n" +
                        "200 grams\n" +
                        "Fabric Type\n" +
                        "Mixed Materials\n" +
                        "Dress Style\n" +
                        "A Line\n" +
                        "Size\n" +
                        "S\n" +
                        "Targeted Group\n" +
                        "Women\n" +
                        "Occasion\n" +
                        "Casual\n",
                "https://cf4.s3.souqcdn.com/item/2019/07/02/55/27/64/51/item_XXL_55276451_00694b5bb8b23.jpg",
                "https://cf4.s3.souqcdn.com/item/2019/07/02/55/27/64/51/item_XXL_55276451_00694b5bb8b23.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                27, 6, "BaiLi Strapless Sweetheart chiffon evening gown dress",
                1150,
                "•\tBrand : BaiLi\n" +
                        "•\tColor : Coral\n" +
                        "•\tFabric Type : Chiffon\n" +
                        "•\tDress Style : Layered\n" +
                        "•\tSize : 8 US\n" +
                        "•\tTargeted Group : Women\n",
                "\n" +
                        "Package thickness\n" +
                        "35.8 centimeters\n" +
                        "Package weight in KGs\n" +
                        "484 grams\n" +
                        "Fabric Type\n" +
                        "Chiffon\n" +
                        "Dress Style\n" +
                        "Layered\n" +
                        "Size\n" +
                        "8 US\n" +
                        "Targeted Group\n" +
                        "Women\n",
                "https://cf1.s3.souqcdn.com/item/2019/04/16/52/09/41/69/item_XXL_52094169_6d22826dc9f54.jpg",
                "https://cf4.s3.souqcdn.com/item/2019/04/16/52/09/41/69/item_XXL_52094169_b0598b64d5356.jpg "))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                28, 6, "Gabriella Round-Neck Short-Sleeve Heart-Print High-Low T-shirt for Women - Light Green, M",
                83,
                "•\tBrand: Gabriella\n" +
                        "•\tType: T-shirt\n" +
                        "•\tMaterial: 50% Cotton - 50% Polyester\n" +
                        "•\tShort sleeves\n" +
                        "•\tRound neck\n",
                "\n" +
                        "Brand\n" +
                        "Gabriella\n" +
                        "Package thickness\n" +
                        "27.6 centimeters\n" +
                        "Package weight in KGs\n" +
                        "180 grams\n" +
                        "Fabric Type\n" +
                        "Mixed Materials\n" +
                        "Top Style\n" +
                        "T-Shirts\n" +
                        "Size\n" +
                        "M\n" +
                        "Targeted Group\n" +
                        "Women\n",
                "https://cf4.s3.souqcdn.com/item/2019/11/13/90/16/86/86/item_XXL_90168686_ad2b2248a4660.jpg",
                "https://cf4.s3.souqcdn.com/item/2019/11/13/90/16/86/86/item_XXL_90168686_ad2b2248a4660.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                29, 6, "Bella Donna Basic Long Sleeve Fitted Shirt for Women - Black",
                359,
                "•\tBrand: Bella Donna\n" +
                        "•\tColor: Black\n" +
                        "•\tMaterial: 70% Cotton - 27% Nylon - 3% Spandex\n" +
                        "•\tTargeted Group: Women\n" +
                        "•\tSize: 50 EU\n",
                "Brand\n" +
                        "Bella Donna\n" +
                        "Package thickness\n" +
                        "32.4 centimeters\n" +
                        "Package weight in KGs\n" +
                        "454 grams\n" +
                        "Fabric Type\n" +
                        "Mixed Materials\n" +
                        "Top Style\n" +
                        "Shirts\n" +
                        "Size\n" +
                        "50 EU\n" +
                        "Targeted Group\n" +
                        "Women\n" +
                        "Sleeve Length\n" +
                        "Full Sleeve\n",
                "https://cf4.s3.souqcdn.com/item/2019/11/13/90/16/86/86/item_XXL_90168686_ad2b2248a4660.jpg",
                "https://cf4.s3.souqcdn.com/item/2019/11/13/90/16/86/86/item_XXL_90168686_ad2b2248a4660.jpg "))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                30, 6, "Bella Cotton Striped Asymmetrical Hem Elastic Waist Skirt for Women - Multi Color, L",
                99,
                "•\tBrand: Bella Cotton\n" +
                        "•\tType: Skirt\n" +
                        "•\tStriped\n" +
                        "•\tAsymmetrical hem\n" +
                        "•\tElastic waist\n",
                "Brand\n" +
                        "Bella Cotton\n" +
                        "Package thickness\n" +
                        "24.4 centimeters\n" +
                        "Package weight in KGs\n" +
                        "120 grams\n" +
                        "Fabric Type\n" +
                        "Mixed Materials\n" +
                        "Skirt Style\n" +
                        "Asymmetrical\n" +
                        "Size\n" +
                        "L\n" +
                        "Targeted Group\n" +
                        "Women\n" +
                        "\n",
                "https://cf1.s3.souqcdn.com/item/2020/06/15/13/10/67/18/2/item_XXL_131067182_6a8e4445e9557.jpg",
                "https://cf1.s3.souqcdn.com/item/2020/06/15/13/10/67/18/2/item_XXL_131067182_6a8e4445e9557.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();




        // Kids Wear '7'


              productDatabase.productDao().insertProduct(new Product(
                31, 7, "Zercon Two Pieces Wear for Boys - Melon",
                220,
                "•\tBrand: Zercon\n" +
                        "•\tType: Two Pieces Wear\n" +
                        "•\tMaterial: Cotton\n" +
                        "•\tSize: 6 Years\n" +
                        "•\tTargeted Group: Boys\n" +
                        "•\tSleeve Length: Short Sleeve\n" +
                        "•\tNeck Style: Round Neck\n" +
                        "•\tColor: Melon\n" +
                        "\n",
                "Brand\n" +
                        "Zercon\n" +
                        "Package thickness\n" +
                        "23.6 centimeters\n" +
                        "Package weight in KGs\n" +
                        "300 grams\n" +
                        "Fabric Type\n" +
                        "Cotton\n" +
                        "Top Style\n" +
                        "Two Pieces Wear\n" +
                        "Size\n" +
                        "6 Years\n" +
                        "Targeted Group\n" +
                        "Boys\n" +
                        "Sleeve Length\n" +
                        "Short Sleeve\n" +
                        "Neck Style\n" +
                        "Round Neck\n" +
                        "Expirable\n" +
                        "Material Composition\n" +
                        "100% Cotton\n" +
                        "Is Adult Product\n" +
                        "Package height\n" +
                        "3.6 centimeters\n" +
                        "Package Width\n" +
                        "21.8 centimeters\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725548038572\n",
                "https://cf4.s3.souqcdn.com/item/2020/06/09/12/60/34/76/7/item_XXL_126034767_4a55902b61949.jpg",
                "https://cf4.s3.souqcdn.com/item/2020/06/09/12/60/34/76/7/item_XXL_126034767_4a55902b61949.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();

        productDatabase.productDao().insertProduct(new Product(
                32, 7, "Lumex Floral Stitched Detail Ruffle Cap Sleeves Dress for Girls - Orange, 12 – 18 Months",
                110,
                "•\tBrand: Lumex\n" +
                        "•\tType: Dress\n" +
                        "•\tRuffle cap sleeves\n" +
                        "•\tFloral stitched detail\n" +
                        "•\tButtoned back\n" +
                        "\n",
                "Brand\n" +
                        "Lumex\n" +
                        "Package thickness\n" +
                        "22.8 centimeters\n" +
                        "Package weight in KGs\n" +
                        "116 grams\n" +
                        "Category\n" +
                        "Dresses\n" +
                        "Size\n" +
                        "12 - 18 Months\n" +
                        "Targeted Group\n" +
                        "Girls\n" +
                        "Expirable\n" +
                        "Package height\n" +
                        "2.6 centimeters\n" +
                        "Package Width\n" +
                        "6.6 centimeters\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725497838391\n" +
                        "\n",
                "https://cf2.s3.souqcdn.com/item/2020/03/17/12/10/52/99/6/item_XL_121052996_4600c89492194.jpg",
                "https://cf4.s3.souqcdn.com/item/2020/03/17/12/10/52/99/6/item_XL_121052996_7ef58ba90e13a.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                33, 7, "Bella Cotton BCS230 Front Print Drawstring Elastic Waist Shorts for Boys - Heather Light Grey, 10 Years",
                129,
                "•\tBrand: Bella Cotton\n" +
                        "•\tType: Drawstring Shorts\n" +
                        "•\tFront print\n" +
                        "•\tElastic waist\n" +
                        "•\tSide pockets\n" +
                        "•\tBack pocket\n" +
                        "\n",
                "Brand\n" +
                        "Bella Cotton\n" +
                        "Color\n" +
                        "Heather Light Grey\n" +
                        "Package thickness\n" +
                        "18 centimeters\n" +
                        "Package weight in KGs\n" +
                        "280 grams\n" +
                        "Fabric Type\n" +
                        "Cotton\n" +
                        "Size\n" +
                        "10 Years\n" +
                        "Short Type\n" +
                        "Drawstring Short\n" +
                        "Targeted Group\n" +
                        "Boys\n" +
                        "Occasion\n" +
                        "Casual\n" +
                        "Model Number\n" +
                        "BCS230\n" +
                        "Expirable\n" +
                        "Material Composition\n" +
                        "100% Cotton\n" +
                        "Is Adult Product\n" +
                        "Package height\n" +
                        "4.2 centimeters\n" +
                        "Package Width\n" +
                        "25.6 centimeters\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725497395214\n" +
                        "\n",
                "https://cf1.s3.souqcdn.com/item/2020/03/16/12/10/08/54/3/item_XXL_121008543_ce8409a4c57eb.jpg",
                "https://cf5.s3.souqcdn.com/item/2020/03/16/12/10/08/54/3/item_XXL_121008543_f4956026db8e2.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                34, 7, "Carrot T-Shirt For Girls - Grey",
                70,
                "••\tBrand:Carrot\n" +
                        "•\tTop style:T-Shirt\n" +
                        "•\tSleeve Length:Cap Sleeves\n" +
                        "•\tNeck Style:Round Neck\n" +
                        "•\tColor:Grey\n" +
                        "•\tFabric Type:Cotton\n" +
                        "•\tSize:4 Years\n" +
                        "•\tTargeted Group:Girls\n" +
                        "\n",
                "Brand\n" +
                        "Carrot\n" +
                        "Package thickness\n" +
                        "17.6 centimeters\n" +
                        "Package weight in KGs\n" +
                        "100 grams\n" +
                        "Fabric Type\n" +
                        "Cotton\n" +
                        "Top Style\n" +
                        "T-Shirts\n" +
                        "Size\n" +
                        "4 Years\n" +
                        "Targeted Group\n" +
                        "Girls\n" +
                        "Sleeve Length\n" +
                        "Cap Sleeves\n" +
                        "Neck Style\n" +
                        "Round Neck\n" +
                        "Expirable\n" +
                        "Material Composition\n" +
                        "100% Cotton\n" +
                        "\n",
                "https://cf2.s3.souqcdn.com/item/2020/04/30/12/29/64/89/9/item_XXL_122964899_dec0796b3bb09.jpg",
                "https://cf2.s3.souqcdn.com/item/2020/04/30/12/29/64/89/9/item_XXL_122964899_dec0796b3bb09.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();


        productDatabase.productDao().insertProduct(new Product(
                35, 7, "Mobaco Cotton Short Sleeve Contrast Chest Logo Polo Shirt for Kids - White, 2 - 3 Years",
                179,
                "•\tBrand: Mobaco\n" +
                        "•\tType: Polo shirt\n" +
                        "•\tTargeted group: Unisex\n" +
                        "•\tEmbroidered chest logo\n" +
                        "•\tRibbed trims\n" +
                        "•\tButton down neck\n" +
                        "•\tSize: 2 - 3 Years\n" +
                        "\n",
                "Brand\n" +
                        "Mobaco\n" +
                        "Package thickness\n" +
                        "29.6 centimeters\n" +
                        "Package weight in KGs\n" +
                        "520 grams\n" +
                        "Category\n" +
                        "T-Shirt\n" +
                        "Size\n" +
                        "2 - 3 Years\n" +
                        "Targeted Group\n" +
                        "Unisex\n" +
                        "Expirable\n" +
                        "Package height\n" +
                        "4 centimeters\n" +
                        "Package Width\n" +
                        "21.8 centimeters\n" +
                        "Serial Scan Required\n" +
                        "Item EAN\n" +
                        "2725528790162\n" +
                        "\n",
                "https://cf3.s3.souqcdn.com/item/2020/05/19/12/41/12/94/9/item_XL_124112949_9d6ecf0e4ab6a.jpg",
                "https://cf4.s3.souqcdn.com/item/2020/05/19/12/41/12/94/9/item_XL_124112949_152c0c26d7627.jpg"))
                .subscribeOn(Schedulers.computation())
                .subscribe();








    }
}