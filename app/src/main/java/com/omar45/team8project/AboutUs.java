package com.omar45.team8project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = findViewById(R.id.toolbarAboutUs);

        toolbar = findViewById(R.id.toolbarAllprod);
//        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About Us");
        }

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .enableDarkMode(false)
                .setDescription(getString(R.string.about_us))
                .setImage(R.drawable.logo_banner)
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with us")
                .addEmail("omr20191700413@cis.asu.edu.eg")
                .addWebsite("FlyBuy.com")
                .addGitHub("omarr45/Team8Project")
                .addItem(new Element().setTitle("Thank you for downloading!"))
                .addItem(new Element().setTitle("Back to home").setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }))
                .create();
        setContentView(aboutPage);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}