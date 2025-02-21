package com.example.yourmeal.dashboard.home.view;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface HomeViewInterface {

    void onRandomMealResponseSuccess(Meal meal);

    void onResponseError(String errorMsg);

    void onAllMealsResponseSuccess(List<Meal> mealsList);
}
