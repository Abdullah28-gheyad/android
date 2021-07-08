package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
                startActivity(new Intent(About.this , Homeacrivity.class));
           }
       },3000);

































        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation) ;
        bottomNavigationView.setSelectedItemId(R.id.about);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.phone_important:
                        startActivity(new Intent(About.this , Important_phone.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Articles_:
                        startActivity(new Intent(About.this , Articles.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.dashboard:
                        startActivity(new Intent(About.this , Dashboard.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.about:
                        return true ;
                    case R.id.Home_:
                        startActivity(new Intent(About.this , Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
    }

    public void Gotrackcountries(View view) {

    }
}