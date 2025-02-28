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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsRemoteDataSource implements MealsRemoteDataSourceInterface{

    APIService apiService;

    public MealsRemoteDataSource(APIService apiService){
        this.apiService = apiService;
    }
    @Override
    public Single<RandomMealResponse> makeRandomMealNetworkCall() {
        return apiService.getRandomMeal();
    }

    @Override
    public Single<AllAreasResponse> getAllMealsAreas() {
        return apiService.getAllMealsAreas();
    }

    @Override
    public Single<AllCategoriesResponse> getAllCategories() {
        return apiService.getAllCategories();
    }

    @Override
    public Single<AllIngredientsResponse> getAllIngredients() {
        return apiService.getAllIngredients();
    }

    @Override
    public Single<RandomMealResponse> makeAllMealsNetworkCall(char character) {
        return apiService.getAllMeals(character);
    }

    @Override
    public Single<CountryMealsResponse> getCountryMeals(String countryName) {
        return apiService.getCountryMeals(countryName);
    }

    @Override
    public Single<IngredientMealsResponse> getIngredientMeals(String ingredientName) {
        return apiService.getIngredientMeals(ingredientName);
    }

    @Override
    public Single<CategoryMealsResponse> getCategoryMeals(String categoryName) {
        return apiService.getCategoryMeals(categoryName);
    }

    @Override
    public Single<MealIdResponse> getMealIdResponse(String idName) {
        return apiService.getMealIdResponse(idName);
    }
}
