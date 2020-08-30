package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class OrderConfirmed extends AppCompatActivity {

    Button backToHome;
    Random order_number_generator;
    TextView order_number;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);


        backToHome = findViewById(R.id.backtohome);
        order_number_generator=new Random();
        order_number =findViewById(R.id.order_number);

        order_number.setText(String.valueOf(order_number_generator.nextInt(1000)));

        final CartDatabase cartDatabase=CartDatabase.getInstance(this);

        cartDatabase.cartDao().deleteAll();

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderConfirmed.this, MainActivity.class));
                finish();
            }
        });


    }



}