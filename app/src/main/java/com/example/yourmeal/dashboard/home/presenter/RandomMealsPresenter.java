package com.example.yourmeal.dashboard.home.presenter;

import com.example.yourmeal.dashboard.home.view.HomeViewInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

public class RandomMealsPresenter implements RandomMealsPresenterInterface {

    HomeViewInterface homeView;

    RepoInterface repo;

    public RandomMealsPresenter(RepoInterface repo, HomeViewInterface homeView){
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

    public void getRandomMeal(){
        repo.getRandomMeal(this);
    }
}
