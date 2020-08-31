package com.omar45.team8project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountData extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, email, birthday, phone;
    FirebaseDatabase database;
    DatabaseReference reference;

    private static final String USER = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_data);

        Intent intent = getIntent();
        final String _email = intent.getStringExtra("email");

        toolbar     = findViewById(R.id.toolbarAccData);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Account Data");
        }

        name        = findViewById(R.id.acc_name);
        email       = findViewById(R.id.acc_email);
        birthday    = findViewById(R.id.acc_birthday);
        phone       = findViewById(R.id.acc_phone);

        database    = FirebaseDatabase.getInstance();
        reference   = database.getReference(USER);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(_email)) {
                        name.setText(ds.child("name").getValue(String.class));
                        email.setText(ds.child("email").getValue(String.class));
                        birthday.setText(ds.child("birthday").getValue(String.class));
                        phone.setText(ds.child("phone").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
