package com.omar45.team8project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    TextView forgotPass, signUp, noLogin;
    Button loginNormal;
    LoginButton loginFB;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        forgotPass = findViewById(R.id.forgotPassword);
        signUp = findViewById(R.id.signUpText);
        noLogin = findViewById(R.id.noLogin);
        loginNormal = findViewById(R.id.signInButton);

        forgotPass.setOnClickListener(this);
        signUp.setOnClickListener(this);
        noLogin.setOnClickListener(this);
        loginNormal.setOnClickListener(this);

        loginFB = findViewById(R.id.fbLogin);

        callbackManager = CallbackManager.Factory.create();
        loginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignInActivity.this, "Login Canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    ///Temporary intents
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.signInButton) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
//            finish();
        }
        if (view.getId()==R.id.forgotPassword) {
            startActivity(new Intent(SignInActivity.this, AccountData.class));
//            finish();
        }
        if (view.getId()==R.id.signUpText) {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
//            finish();
        }
        if (view.getId()==R.id.noLogin) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
//            finish();
        }
    }
}