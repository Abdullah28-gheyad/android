package com.example.covid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid.Adapter.CountryAdapter;
import com.example.covid.Model.AllcountriesModel.CountryInfo;
import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity2 extends AppCompatActivity {

    TextView cases , today_cases , death, today_death , recoverd ,active , critical , affected_countries ,Global_states;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        cases = (TextView)findViewById(R.id.tv_cases) ;
        today_cases = (TextView)findViewById(R.id.tv_todaycases) ;
        death = (TextView)findViewById(R.id.tvdeath) ;
        today_death = (TextView)findViewById(R.id.tv_todaydeath) ;
        recoverd = (TextView)findViewById(R.id.tv_recoverd) ;
        active = (TextView)findViewById(R.id.tv_active) ;
        critical = (TextView)findViewById(R.id.tv_critical) ;
        affected_countries = (TextView)findViewById(R.id.tv_affected_countries) ;
        Global_states = (TextView) findViewById(R.id.Global_states) ;
        Intent intent = getIntent() ;
        String countryName = intent.getStringExtra("countryName") ;
        ActionBar actionBar = getSupportActionBar() ;
        actionBar.setTitle(countryName);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Global_states.setText(countryName + " "+"States");
        ApiCall(countryName) ;
    }



    private void ApiCall(String countryName) {
        String Url = "https://corona.lmao.ninja/v2/countries/"+countryName;
        StringRequest request= new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response) ;
                            cases.setText(jsonObject.getString("cases"));
                            today_cases.setText(jsonObject.getString("todayCases"));
                            death.setText(jsonObject.getString("deaths"));
                            today_death.setText(jsonObject.getString("todayDeaths"));
                            recoverd.setText(jsonObject.getString("recovered"));
                            active.setText(jsonObject.getString("active"));
                            critical.setText(jsonObject.getString("critical"));
                            cases.setText(jsonObject.getString("cases"));
                            Log.d("cases", jsonObject.getString("cases"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity2.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }) ;
        RequestQueue requestQueue = Volley.newRequestQueue(this) ;
        requestQueue.add(request);

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(DetailActivity2.this , AllCountriesActivity.class) ;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
        startActivity(intent);
        super.onBackPressed();
    }
}