package com.example.yourmeal.dashboard.home.presenter;

import com.example.yourmeal.dashboard.home.view.HomeViewInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.List;
import java.util.Random;

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

    @Override
    public void getRandomMeal(){
        repo.getRandomMeal(this);
    }

    @Override
    public void onAllMealsResponseSuccess(List<Meal> allMealsResponseList) {
        homeView.onAllMealsResponseSuccess(allMealsResponseList);
    }

    @Override
    public void getAllMeals(){
        repo.getAllMeals(this, getRandomCharacter());
    }

    private char getRandomCharacter() {
        Random random = new Random();
        return (char) ('a' + random.nextInt(26));
    }
}
