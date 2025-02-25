package com.example.yourmeal.local;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealsLocalDataSourceInterface {

    Completable addMeal(Meal meal);

    Flowable<List<Meal>> getMeals();
    Completable removeMeal(Meal meal);

    Flowable<Meal> getMealById(String idMeal);




}
