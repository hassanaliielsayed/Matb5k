package com.example.yourmeal.network;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/json/v1/1/random.php")
    Single<RandomMealResponse> getRandomMeal();

    @GET("api/json/v1/1/search.php") // becomes api/json/v1/1/search.php?f=randomChar
    Single<RandomMealResponse> getAllMeals(
            @Query("f") char randomChar
    );


}
