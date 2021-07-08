package com.example.covid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    public  String hospital_name  = "";
    FloatingActionButton floating_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        floating_btn = (FloatingActionButton)findViewById(R.id.floating_button) ;
        floating_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(Dashboard.this, "Internet Connection faild check network and try again ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation) ;
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.phone_important:
                        startActivity(new Intent(Dashboard.this , Important_phone.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Articles_:
                        startActivity(new Intent(Dashboard.this , Articles.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.dashboard:
                        return true ;
                    case R.id.about:

                        startActivity(new Intent(Dashboard.this , About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Home_:

                        startActivity(new Intent(Dashboard.this , Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
        BottomNavigationView bottomNavigationView2 = findViewById(R.id.fragment_hospital) ;
        bottomNavigationView2.setSelectedItemId(R.id.hospitals);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.hospitals:
                        return true ;
                    case  R.id.hospitalgps:
                        Intent intent = new Intent(Dashboard.this , Hospital_gps.class);
                        intent.putExtra("hospital_name" ,hospital_name) ;
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                }
                return false;
            }
        });


        ListView listView = (ListView)findViewById(R.id.listof_hospitals)  ;
        ArrayAdapter<String> listadapter= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        listView.setAdapter(listadapter);
        listadapter.addAll(getResources().getStringArray(R.array.hospitals));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hospital_name =  listadapter.getItem(position).toString()  ;
                Intent intent = new Intent(Dashboard.this , Hospital_gps.class);
                intent.putExtra("hospital_name" ,hospital_name) ;
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}