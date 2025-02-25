package com.example.yourmeal.dashboard.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface FavoritePresenterInterface {

    void removeMeal(Meal meal);

    void getMealsLocally();
}
