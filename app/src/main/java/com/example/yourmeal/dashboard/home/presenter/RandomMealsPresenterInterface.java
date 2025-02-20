package com.example.yourmeal.dashboard.home.presenter;

import com.example.yourmeal.model.Meal;

public interface RandomMealsPresenterInterface {

    void onRandomMealResponseSuccess(Meal meal);

    void onResponseError(String errorMsg);

    void getRandomMeal();
}
