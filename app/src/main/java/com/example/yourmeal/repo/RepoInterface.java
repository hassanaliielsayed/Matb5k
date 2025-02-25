package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.model.RandomMealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface RepoInterface {
    Single<RandomMealResponse> getRandomMeal();

    Single<RandomMealResponse> getAllMeals(char character);

    Completable insertMeal(Meal meal);

    Flowable<List<Meal>> getStoredMeals();

    Completable deleteMeal(Meal meal);

    Flowable<Meal> getMealById(String idMeal);



}
