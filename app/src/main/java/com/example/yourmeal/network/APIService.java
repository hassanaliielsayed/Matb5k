package com.example.yourmeal.network;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.AllAreasResponse;
import com.example.yourmeal.model.AllCategoriesResponse;
import com.example.yourmeal.model.AllIngredientsResponse;
import com.example.yourmeal.model.CategoryMealsResponse;
import com.example.yourmeal.model.CountryMealsResponse;
import com.example.yourmeal.model.IngredientMealsResponse;
import com.example.yourmeal.model.MealIdResponse;
import com.example.yourmeal.model.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/json/v1/1/random.php")
    Single<RandomMealResponse> getRandomMeal();


    @GET("api/json/v1/1/list.php?a=list")
    Single<AllAreasResponse> getAllMealsAreas();


    @GET("api/json/v1/1/list.php?c=list")
    Single<AllCategoriesResponse> getAllCategories();

    @GET("api/json/v1/1/list.php?i=list")
    Single<AllIngredientsResponse> getAllIngredients();



    @GET("api/json/v1/1/search.php") // becomes api/json/v1/1/search.php?f=randomChar
    Single<RandomMealResponse> getAllMeals(
            @Query("f") char randomChar
    );

    @GET("api/json/v1/1/filter.php") // becomes api/json/v1/1/filter.php?a=areaName
    Single<CountryMealsResponse> getCountryMeals(
            @Query("a") String countryName
    );

    @GET("api/json/v1/1/filter.php") // becomes api/json/v1/1/filter.php?i=ingredientName
    Single<IngredientMealsResponse> getIngredientMeals(
            @Query("i") String ingredientName
    );

    @GET("api/json/v1/1/filter.php") // becomes api/json/v1/1/filter.php?c=categoryName
    Single<CategoryMealsResponse> getCategoryMeals(
            @Query("c") String categoryName
    );

    @GET("api/json/v1/1/lookup.php") // api/json/v1/1/lookup.php?i=idMeal
    Single<MealIdResponse> getMealIdResponse(
            @Query("i") String idMeal
    );










}
