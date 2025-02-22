package com.example.yourmeal.local;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface MealsLocalDataSourceInterface {

    void addMeal(Meal meal);

    LiveData<List<Meal>> getMeals();

    void removeMeal(Meal meal);


}
