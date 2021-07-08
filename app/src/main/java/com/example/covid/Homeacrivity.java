package com.example.covid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.covid.Model.GlobalResponse;
import com.example.covid.Network.ApiClientPrivate;
import com.example.covid.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Homeacrivity extends AppCompatActivity {
    TextView cases , today_cases , death, today_death , recoverd ,active , critical , affected_countries ;
   Button contrytracker ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeacrivity);
       cases = (TextView)findViewById(R.id.tv_cases) ;
        today_cases = (TextView)findViewById(R.id.tv_todaycases) ;
        death = (TextView)findViewById(R.id.tvdeath) ;
        today_death = (TextView)findViewById(R.id.tv_todaydeath) ;
        recoverd = (TextView)findViewById(R.id.tv_recoverd) ;
        active = (TextView)findViewById(R.id.tv_active) ;
        critical = (TextView)findViewById(R.id.tv_critical) ;
        affected_countries = (TextView)findViewById(R.id.tv_affected_countries) ;
        contrytracker = (Button)findViewById(R.id.countrey_tracker) ;
        contrytracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homeacrivity.this , AllCountriesActivity.class));
            }
        });
        apiCall() ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void apiCall() {
        ApiInterface apiInterface = null ;
        apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class) ;
        Call<GlobalResponse> call = apiInterface.globalresponse() ;
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {

                if (response.isSuccessful())
                {
                    String st_case= String.valueOf(response.body().getCases());
                    String st_todaycase= String.valueOf(response.body().getTodayCases());
                    String st_death= String.valueOf(response.body().getDeaths());
                    String st_todaydeath= String.valueOf(response.body().getTodayDeaths());
                    String st_recovered= String.valueOf(response.body().getRecovered());
                    String st_active= String.valueOf(response.body().getActive());
                    String st_critical= String.valueOf(response.body().getCritical());
                    String st_affectedcountries= String.valueOf(response.body().getAffectedCountries());
                    cases.setText(st_case);
                    today_cases.setText(st_todaycase);
                    death.setText(st_death);
                    today_death.setText(st_todaydeath);
                    recoverd.setText(st_recovered);
                    active.setText(st_active);
                    critical.setText(st_critical);
                    affected_countries.setText(st_affectedcountries);
                    Log.d("cases",st_case) ;

                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {

            }
        });
    }


}











