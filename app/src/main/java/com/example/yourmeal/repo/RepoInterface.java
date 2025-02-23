package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.Meal;

import java.util.List;

public interface RepoInterface {
    void getRandomMeal(HomePresenterInterface randomMealsPresenter);

    void getAllMeals(HomePresenterInterface randomMealsPresenter, char character);

    void insertMeal(Meal meal);

    LiveData<List<Meal>> getStoredMeals();

    void deleteMeal(Meal meal);

    LiveData<Meal> getMealById(String idMeal);



}
