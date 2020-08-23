package com.omar45.team8project;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationOrder extends AppCompatActivity implements View.OnClickListener {
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_order);

        confirm =findViewById(R.id.confirm_button);

        confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(ConfirmationOrder.this, OrderConfirmed.class));

    }
}
