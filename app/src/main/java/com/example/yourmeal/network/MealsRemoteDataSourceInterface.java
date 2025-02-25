package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSourceInterface {

    Single<RandomMealResponse> makeRandomMealNetworkCall();
    Single<RandomMealResponse> makeAllMealsNetworkCall(char character);
}
