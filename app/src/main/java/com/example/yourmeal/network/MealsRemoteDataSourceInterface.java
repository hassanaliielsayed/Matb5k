package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.AllAreasResponse;
import com.example.yourmeal.model.AllCategoriesResponse;
import com.example.yourmeal.model.AllIngredientsResponse;
import com.example.yourmeal.model.CategoryMealsResponse;
import com.example.yourmeal.model.CountryMealsResponse;
import com.example.yourmeal.model.IngredientMealsResponse;
import com.example.yourmeal.model.MealIdResponse;
import com.example.yourmeal.model.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSourceInterface {

    Single<RandomMealResponse> makeRandomMealNetworkCall();

    Single<AllAreasResponse> getAllMealsAreas();
    Single<AllCategoriesResponse> getAllCategories();

    Single<AllIngredientsResponse> getAllIngredients();
    Single<RandomMealResponse> makeAllMealsNetworkCall(char character);

    Single<CountryMealsResponse> getCountryMeals(String countryName);

    Single<IngredientMealsResponse> getIngredientMeals(String ingredientName);

    Single<CategoryMealsResponse> getCategoryMeals(String categoryName);

    Single<MealIdResponse> getMealIdResponse(String idName);


}
