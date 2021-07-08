package com.example.covid.Network;

import com.example.covid.Model.GlobalResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<GlobalResponse> globalresponse() ;

}
