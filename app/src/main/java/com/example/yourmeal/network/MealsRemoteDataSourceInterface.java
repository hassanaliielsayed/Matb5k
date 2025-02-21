package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;

public interface MealsRemoteDataSourceInterface {

    void makeRandomMealNetworkCall(HomePresenterInterface randomMealsPresenter);
    void makeAllMealsNetworkCall(HomePresenterInterface randomMealsPresenter, char character);
}
