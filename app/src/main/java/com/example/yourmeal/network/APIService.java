package com.example.yourmeal.network;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.RandomMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("api/json/v1/1/random.php")
    Call<RandomMealResponse> getRandomMeal();
}
