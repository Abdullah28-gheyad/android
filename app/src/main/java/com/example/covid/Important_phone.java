package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Important_phone extends AppCompatActivity {
    TextView phone1  , phone2 , link1,link2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_phone);
       phone1 = (TextView)findViewById(R.id.phone_1) ;
        phone2 = (TextView)findViewById(R.id.phone_2) ;
        link1 = (TextView)findViewById(R.id.link1) ;
        link2 = (TextView)findViewById(R.id.link2);
        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2.setMovementMethod(LinkMovementMethod.getInstance());




       phone1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "105", null)));
           }
       });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "15335", null)));
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation) ;
        bottomNavigationView.setSelectedItemId(R.id.phone_important);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.phone_important:
                        return true ;
                    case R.id.Articles_:
                        startActivity(new Intent(Important_phone.this , Articles.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.dashboard:
                        startActivity(new Intent(Important_phone.this , Dashboard.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.about:
                        startActivity(new Intent(Important_phone.this , About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Home_:
                        startActivity(new Intent(Important_phone.this , Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
    }
}