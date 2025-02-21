package com.example.yourmeal.dashboard.home.presenter;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface HomePresenterInterface {

    void onRandomMealResponseSuccess(Meal meal);

    void onResponseError(String errorMsg);

    void getRandomMeal();

    void onAllMealsResponseSuccess(List<Meal> allMealsResponseList);

    void getAllMeals();
}
