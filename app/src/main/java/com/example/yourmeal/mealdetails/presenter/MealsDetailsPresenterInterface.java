package com.example.yourmeal.mealdetails.presenter;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

public interface MealsDetailsPresenterInterface {
    String extractVideoId(String youTubeURL);

    void addMealToFav(Meal meal);

    LiveData<Meal> getMealById(String idMeal);

    void removeMealFromFav(Meal meal);


}
