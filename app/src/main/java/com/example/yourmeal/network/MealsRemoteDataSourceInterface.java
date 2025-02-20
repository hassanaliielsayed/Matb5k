package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenterInterface;

public interface MealsRemoteDataSourceInterface {

    void makeNetworkCall(RandomMealsPresenterInterface randomMealsPresenter);
}
