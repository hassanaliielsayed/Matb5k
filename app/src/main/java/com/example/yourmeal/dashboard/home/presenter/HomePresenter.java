package com.example.yourmeal.dashboard.home.presenter;

import android.annotation.SuppressLint;

import com.example.yourmeal.dashboard.home.view.HomeViewInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomePresenterInterface {

    HomeViewInterface homeView;

    RepoInterface repo;

    public HomePresenter(RepoInterface repo, HomeViewInterface homeView){
        this.repo = repo;
        this.homeView = homeView;
    }
    @Override
    public void onRandomMealResponseSuccess(Meal meal) {
        homeView.onRandomMealResponseSuccess(meal);
    }

    @Override
    public void onResponseError(String errorMsg) {
        homeView.onResponseError(errorMsg);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getRandomMeal(){
        repo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((randomMealResponse, throwable) -> {
                    if (randomMealResponse != null){
                        homeView.onRandomMealResponseSuccess(randomMealResponse.getMeals().get(0));
                    } else if (throwable != null) {
                        homeView.onResponseError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void onAllMealsResponseSuccess(List<Meal> allMealsResponseList) {
        homeView.onAllMealsResponseSuccess(allMealsResponseList);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMeals(){
        repo.getAllMeals(getRandomCharacter())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((randomMealResponse, throwable) -> {
                    if (randomMealResponse != null){
                        homeView.onAllMealsResponseSuccess(randomMealResponse.getMeals());
                    } else if (throwable != null) {
                        homeView.onResponseError(throwable.getMessage());
                    }
                });
    }

    private char getRandomCharacter() {
        Random random = new Random();
        return (char) ('a' + random.nextInt(26));
    }
}
