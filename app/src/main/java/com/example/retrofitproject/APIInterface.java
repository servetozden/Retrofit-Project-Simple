package com.example.retrofitproject;

import com.example.retrofitproject.model.MultipleResourceModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    String BASE_URL = "https://reqres.in";

    @GET("/api/unknown")
    Call<MultipleResourceModel> getListResources();

}
