package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.model.RandomMealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsRemoteDataSource implements MealsRemoteDataSourceInterface{

    APIService apiService;

    public MealsRemoteDataSource(APIService apiService){
        this.apiService = apiService;
    }
    @Override
    public Single<RandomMealResponse> makeRandomMealNetworkCall() {
        return apiService.getRandomMeal();
    }

    @Override
    public Single<RandomMealResponse> makeAllMealsNetworkCall(char character) {
        return apiService.getAllMeals(character);
    }
}
