package com.example.yourmeal.repo;

import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenterInterface;

public interface RepoInterface {
    void getRandomMeal(RandomMealsPresenterInterface randomMealsPresenter);
}
