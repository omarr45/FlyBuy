package com.omar45.team8project;

import android.annotation.SuppressLint;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ConfirmationOrder extends AppCompatActivity implements View.OnClickListener {
    Button confirm;
    double Longitude;
    double Latitude;
    //TextView locationTxt;
    ArrayList<Address> addresses;
    Geocoder geocoder;
    Intent receive;
    //TextView second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_order);


        //String add = "";

       //second = (TextView) findViewById(R.id.myTxtlocation);






       receive = getIntent();
        Longitude = receive.getDoubleExtra("Longitude" , 31.2025);
        Latitude  = receive.getDoubleExtra("Latitude" , 30.0395);

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            addresses  = (ArrayList<Address>) geocoder.getFromLocation(Latitude, Longitude,1);

            if (addresses != null && addresses.size()>0)
            {
                if (addresses.get(0).getThoroughfare() != null)
                {

                    Toast.makeText(this,addresses.get(0).getThoroughfare() , Toast.LENGTH_SHORT).show();
                     //add = addresses.get(0).getThoroughfare().toString();
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

//       try {
//           second.setText(add);
//       }
//       catch(Exception e)
//       {
//           e.printStackTrace();
//       }

        confirm =findViewById(R.id.confirm_button);

        confirm.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(ConfirmationOrder.this, OrderConfirmed.class));

    }

    public void setLocation(View view)
    {
        Intent intent = new Intent(ConfirmationOrder.this , MapsActivity.class);
        startActivity(intent);


    }
}
