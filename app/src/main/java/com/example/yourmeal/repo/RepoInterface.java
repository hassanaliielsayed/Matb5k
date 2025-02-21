package com.example.yourmeal.repo;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;

public interface RepoInterface {
    void getRandomMeal(HomePresenterInterface randomMealsPresenter);

    void getAllMeals(HomePresenterInterface randomMealsPresenter, char character);
}
