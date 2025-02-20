package com.example.yourmeal.dashboard.home.view;

import com.example.yourmeal.model.Meal;

public interface HomeViewInterface {

    void onRandomMealResponseSuccess(Meal meal);

    void onResponseError(String errorMsg);
}
