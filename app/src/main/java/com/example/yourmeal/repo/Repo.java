package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.local.MealsLocalDataSourceInterface;
import com.example.yourmeal.model.AllAreasResponse;
import com.example.yourmeal.model.AllCategoriesResponse;
import com.example.yourmeal.model.AllIngredientsResponse;
import com.example.yourmeal.model.CategoryMealsResponse;
import com.example.yourmeal.model.CountryMealsResponse;
import com.example.yourmeal.model.IngredientMealsResponse;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.model.MealIdResponse;
import com.example.yourmeal.model.RandomMealResponse;
import com.example.yourmeal.network.MealsRemoteDataSourceInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class Repo implements RepoInterface {

    private volatile static RepoInterface instance = null;
    private MealsRemoteDataSourceInterface mealsRemoteDataSource;
    private MealsLocalDataSourceInterface mealsLocalDataSource;

    private Repo(MealsRemoteDataSourceInterface mealsRemoteDataSource, MealsLocalDataSourceInterface mealsLocalDataSource){
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public static RepoInterface getInstance(MealsRemoteDataSourceInterface mealsRemoteDataSource, MealsLocalDataSourceInterface mealsLocalDataSource){
        if (instance == null){
            synchronized (Repo.class){
                if (instance == null){
                    instance = new Repo(mealsRemoteDataSource, mealsLocalDataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public Single<RandomMealResponse> getRandomMeal(){
        return mealsRemoteDataSource.makeRandomMealNetworkCall();
    }

    @Override
    public Single<RandomMealResponse> getAllMeals(char character) {
        return mealsRemoteDataSource.makeAllMealsNetworkCall(character);
    }

    @Override
    public Single<AllAreasResponse> getAllMealsAreas() {
        return mealsRemoteDataSource.getAllMealsAreas();
    }

    @Override
    public Single<AllCategoriesResponse> getAllCategories() {
        return mealsRemoteDataSource.getAllCategories();
    }

    @Override
    public Single<AllIngredientsResponse> getAllIngredients() {
        return mealsRemoteDataSource.getAllIngredients();
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return mealsLocalDataSource.addMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals(String email) {
        return (mealsLocalDataSource.getMeals(email));
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return mealsLocalDataSource.removeMeal(meal);
    }

    @Override
    public Flowable<Meal> getMealById(String idMeal, String email) {
        return (mealsLocalDataSource.getMealById(idMeal, email));
    }

    @Override
    public Single<CountryMealsResponse> getCountryMeals(String countryName) {
        return mealsRemoteDataSource.getCountryMeals(countryName);
    }

    @Override
    public Single<IngredientMealsResponse> getIngredientMeals(String ingredientName) {
        return mealsRemoteDataSource.getIngredientMeals(ingredientName);
    }

    @Override
    public Single<CategoryMealsResponse> getCategoryMeals(String categoryName) {
        return mealsRemoteDataSource.getCategoryMeals(categoryName);
    }

    @Override
    public Single<MealIdResponse> getMealIdResponse(String idName) {
        return mealsRemoteDataSource.getMealIdResponse(idName);
    }

    @Override
    public Completable updateMeal(Meal meal) {
        return mealsLocalDataSource.updateMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getUpcomingMeals(String selectedDate) {
        return mealsLocalDataSource.getUpcomingMeals(selectedDate);
    }


}
