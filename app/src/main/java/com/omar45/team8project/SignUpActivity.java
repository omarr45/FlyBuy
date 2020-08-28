package com.omar45.team8project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Calendar cal;
    EditText name, birthday, number, email, password, confPassword;
    Button signUp;
    TextView signIn;
    ProgressBar progressBar;
    DatePickerDialog.OnDateSetListener mDate;
    User user;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;

    private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //EditTexts
        name        = findViewById(R.id.name);
        birthday    = findViewById(R.id.birthday);
        number      = findViewById(R.id.phoneNumber);
        email       = findViewById(R.id.email);
        password    = findViewById(R.id.password);
        confPassword = findViewById(R.id.confirmPassword);

        //TextViews
        signIn      = findViewById(R.id.signInText);

        //Buttons
        signUp      = findViewById(R.id.signUpButton);

        //Calendar
        cal         = Calendar.getInstance();

        //ProgressBar
        progressBar = findViewById(R.id.progressBar);

        //FireBase
        fAuth       = FirebaseAuth.getInstance();
        database    = FirebaseDatabase.getInstance();
        userRef     = database.getReference(USERS);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        //OnClickListeners
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        birthday.setOnClickListener(this);

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthday.setText(sdf.format(cal.getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInText:
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                break;

            case R.id.birthday:
                new DatePickerDialog(SignUpActivity.this, mDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.signUpButton:
                String _name = name.getText().toString().trim();
                String _birthday = birthday.getText().toString().trim();
                String _number = number.getText().toString().trim();
                final String _email = email.getText().toString().trim();
                String _password = password.getText().toString().trim();
                String _passwordConfirm = confPassword.getText().toString().trim();
                user = new User(_name, _birthday, _number, _email, _password);

                if(_name.isEmpty()) {
                    name.setError("Name is required");
                    return;
                }
                if(_birthday.isEmpty()) {
                    birthday.setError("Birthday is required");
                    return;
                }
                if(_number.isEmpty()) {
                    number.setError("Phone Number is required");
                    return;
                }
                if(_email.isEmpty()) {
                    email.setError("Email is required");
                    return;
                }
                if(_password.isEmpty()) {
                    password.setError("Password is required");
                    return;
                }
                if (_password.length()<=6){
                    password.setError("Password length must be more than 6");
                    return;
                }
                if (!_password.equals(_passwordConfirm)) {
                    confPassword.setError("Passwords don't match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(_email,_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser fUser = fAuth.getCurrentUser();
                            updateDB(fUser);
                            Intent intentMain = new Intent(SignUpActivity.this, MainActivity.class);
                            intentMain.putExtra("email", _email);
                            startActivity(intentMain);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
        }
    }

    private void updateDB(FirebaseUser fUser) {
        String keyID = userRef.push().getKey();
        userRef.child(keyID).setValue(user);
    }
}