package com.example.yourmeal.mealdetails.presenter;

import com.example.yourmeal.model.Meal;

public interface MealsDetailsPresenterInterface {
    String extractVideoId(String youTubeURL);

    void addProductToFav(Meal meal);


}
