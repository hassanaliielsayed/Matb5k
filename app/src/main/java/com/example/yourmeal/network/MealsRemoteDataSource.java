package com.example.yourmeal.network;

import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenterInterface;
import com.example.yourmeal.model.RandomMealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsRemoteDataSource implements MealsRemoteDataSourceInterface{

    APIService apiService;

    public MealsRemoteDataSource(APIService apiService){
        this.apiService = apiService;
    }
    @Override
    public void makeNetworkCall(RandomMealsPresenterInterface randomMealsPresenter) {
        apiService.getRandomMeal().enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() == null){
                        randomMealsPresenter.onResponseError("No Meal Found");
                    } else {
                        randomMealsPresenter.onRandomMealResponseSuccess(response.body().getMeals().get(0));
                    }
                } else {
                    randomMealsPresenter.onResponseError(response.message());
                }
            }

            @Override
            public void onFailure(Call<RandomMealResponse> call, Throwable throwable) {
                randomMealsPresenter.onResponseError(throwable.getMessage());
            }
        });
    }
}
