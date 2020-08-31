package com.omar45.team8project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Insert extends AppCompatActivity implements ProductClickListener {
           RecyclerView p_recyclerView;
           Button set_button, get_button;
           EditText p_name,p_price,p_description,p_specs,p_img,c_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_database_activity);

        set_button=findViewById(R.id.set_button);
        get_button=findViewById(R.id.get_button);

        p_name=findViewById(R.id.p_name);
        p_price=findViewById(R.id.p_price);
        p_description=findViewById(R.id.p_description);
        p_specs=findViewById(R.id.p_specs);
        p_img=findViewById(R.id.p_img1);


        p_recyclerView=findViewById(R.id.p_recyclerview);
        final ProductsAdapter adapter = new ProductsAdapter(this, this);
        p_recyclerView.setAdapter(adapter);

        final ProductDatabase productDatabase = ProductDatabase.getInstance(this);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productDatabase.productDao().insertProduct(new Product(1, 2, p_name.getEditableText().toString(),
                        Integer.parseInt(p_price.getEditableText().toString()),p_description.getEditableText().toString(),
                        p_specs.getEditableText().toString(),p_img.getEditableText().toString(), "url"))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });

            }
        });


        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDatabase.productDao().getProducts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Product>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Product>products) {

                                adapter.setList(products);
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

}

    @Override
    public void onClickProduct(Product product) {

    }
}