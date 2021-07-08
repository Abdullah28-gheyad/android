package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Articles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation) ;
        bottomNavigationView.setSelectedItemId(R.id.Articles_);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.phone_important:
                        startActivity(new Intent(Articles.this , Important_phone.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Articles_:
                        return true ;
                    case R.id.dashboard:
                        startActivity(new Intent(Articles.this , Dashboard.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.about:
                        startActivity(new Intent(Articles.this , About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Home_:
                        startActivity(new Intent(Articles.this , Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
    }

}