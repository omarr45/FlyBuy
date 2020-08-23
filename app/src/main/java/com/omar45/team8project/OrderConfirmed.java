package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class OrderConfirmed extends AppCompatActivity {
     Random order_number_generator;
     TextView order_number;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);

        order_number_generator=new Random();
        order_number =findViewById(R.id.order_number);

        order_number.setText(String.valueOf(order_number_generator.nextInt(1000)));

    }



}