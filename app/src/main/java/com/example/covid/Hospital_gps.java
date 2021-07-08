package com.example.covid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
public class Hospital_gps extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map  ;
    SupportMapFragment mapFragment ;
    SearchView searchView ;
    String hospital ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_gps);
        hospital = getIntent().getStringExtra("hospital_name") ;
      //  Toast.makeText(this, "You clicked "+hospital, Toast.LENGTH_SHORT).show();
        searchView= findViewById(R.id.sv_location) ;
        searchView.setQuery(hospital,false);
        mapFragment=(SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.google_map) ;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location= searchView.getQuery().toString() ;
                List<Address> addressList = null ;
                if (location!=null||!location.equals(""))
                {
                    map.clear();
                    if (isNetworkAvailable())
                    {
                        Geocoder geocoder = new Geocoder(Hospital_gps.this ) ;
                        try{
                            addressList = geocoder.getFromLocationName(location,1) ;
                        } catch (IOException e) {
                            Toast.makeText(Hospital_gps.this, "من فضلك أتصل بالانترنت ", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0) ;
                        LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude()) ;
                        map.addMarker(new MarkerOptions().position(latLng).title(location)) ;
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 10));

                    }
                    else
                    {
                        Toast.makeText(Hospital_gps.this, "Connection error check internet connection", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);








        BottomNavigationView bottomNavigationView2 = findViewById(R.id.fragment_hospital) ;
        bottomNavigationView2.setSelectedItemId(R.id.hospitalgps);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.hospitalgps:
                        return true ;
                    case  R.id.hospitals:
                        Intent intent = new Intent(Hospital_gps.this , Dashboard.class) ;
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                }
                return false;
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
                        startActivity(new Intent(Hospital_gps.this , Important_phone.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Articles_:
                        startActivity(new Intent(Hospital_gps.this , Articles.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.dashboard:
                        return true ;
                    case R.id.about:

                        startActivity(new Intent(Hospital_gps.this , About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Home_:

                        startActivity(new Intent(Hospital_gps.this , Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                }
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap ;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}