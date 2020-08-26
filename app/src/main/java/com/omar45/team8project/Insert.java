package com.omar45.team8project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Insert extends AppCompatActivity {
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
       // c_id=findViewById(R.id.c_id);
        p_img=findViewById(R.id.p_img1);


        p_recyclerView=findViewById(R.id.p_recyclerview);
        final ProductsAdapter adapter = new ProductsAdapter(this);
        p_recyclerView.setAdapter(adapter);

        final ProductDatabase productDatabase = ProductDatabase.getInstance(this);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productDatabase.productDao().insertProduct(new Product(p_name.getEditableText().toString(),
                        p_price.getEditableText().toString(),p_description.getEditableText().toString(),
                        p_specs.getEditableText().toString(),p_img.getEditableText().toString()))
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
                productDatabase.productDao().getProduct()
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
}