package com.omar45.team8project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    TextView forgotPass, signUp, noLogin;
    Button loginNormal;
    LoginButton loginFB;
    CallbackManager callbackManager;

    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //EditTexts
        email       = findViewById(R.id.email_login);
        password    = findViewById(R.id.password_login);
        //TextViews
        forgotPass  = findViewById(R.id.forgotPassword);
        signUp      = findViewById(R.id.signUpText);
        noLogin     = findViewById(R.id.noLogin);
        //Buttons
        loginNormal = findViewById(R.id.signInButton);

        fAuth       = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);

        //OnClickListeners
        forgotPass.setOnClickListener(this);
        signUp.setOnClickListener(this);
        noLogin.setOnClickListener(this);
        loginNormal.setOnClickListener(this);

        ////////////////////////////////////////////////////////////// Enable this to jump over login screen if already logged in
//        //FireBase
//        if (fAuth.getCurrentUser()!=null) {
//            startActivity(new Intent(SignInActivity.this, MainActivity.class));
//            finish();
//        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Facebook Stuff
        callbackManager = CallbackManager.Factory.create();
        loginFB = findViewById(R.id.fbLogin);
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

    //Facebook Stuff Cont.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    ///Temporary intents
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInButton:
                String _email = email.getText().toString().trim();
                String _password = password.getText().toString().trim();

                if(_email.isEmpty()) {
                    email.setError("Email is required");
                    return;
                }
                if(_password.isEmpty()) {
                    password.setError("Password is required");
                    return;
                }
                if(_password.length()<6) {
                    password.setError("Password length must be more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(_email,_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(SignInActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(SignInActivity.this, ShowProduct.class));
//                finish();
                break;

            case R.id.signUpText:
                startActivity(new Intent(SignInActivity.this, SignInActivity.class));
//                finish();
                break;

            case R.id.noLogin:
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                finish();
                break;
        }

    }
}