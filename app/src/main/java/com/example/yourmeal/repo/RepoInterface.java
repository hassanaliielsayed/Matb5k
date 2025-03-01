package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.AllAreasResponse;
import com.example.yourmeal.model.AllCategoriesResponse;
import com.example.yourmeal.model.AllIngredientsResponse;
import com.example.yourmeal.model.CategoryMealsResponse;
import com.example.yourmeal.model.CountryMealsResponse;
import com.example.yourmeal.model.IngredientMealsResponse;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.model.MealIdResponse;
import com.example.yourmeal.model.RandomMealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface RepoInterface {
    Single<RandomMealResponse> getRandomMeal();

    Single<RandomMealResponse> getAllMeals(char character);

    Single<AllAreasResponse> getAllMealsAreas();
    Single<AllCategoriesResponse> getAllCategories();
    Single<AllIngredientsResponse> getAllIngredients();

    Completable insertMeal(Meal meal);

    Flowable<List<Meal>> getStoredMeals(String email);

    Completable deleteMeal(Meal meal);

    Flowable<Meal> getMealById(String idMeal, String email);

    Single<CountryMealsResponse> getCountryMeals(String countryName);
    Single<IngredientMealsResponse> getIngredientMeals(String ingredientName);
    Single<CategoryMealsResponse> getCategoryMeals(String categoryName);

    Single<MealIdResponse> getMealIdResponse(String idName);

    Completable updateMeal(Meal meal);

    Flowable<List<Meal>> getUpcomingMeals(String selectedDate);



}
