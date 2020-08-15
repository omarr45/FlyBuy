package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountData extends AppCompatActivity {

    Button edit;
    EditText name, email, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_data);

        edit = findViewById(R.id.editBut);

        name = findViewById(R.id.name_edit);
        email = findViewById(R.id.email_edit);

        name.setInputType(InputType.TYPE_NULL);
        email.setInputType(InputType.TYPE_NULL);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                name.hasFocus();
            }
        });

    }


}
