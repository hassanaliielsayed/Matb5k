package com.example.yourmeal.repo;

import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenterInterface;
import com.example.yourmeal.network.MealsRemoteDataSourceInterface;

public class Repo implements RepoInterface {

    private volatile static RepoInterface instance = null;
    private MealsRemoteDataSourceInterface mealsRemoteDataSource;

    private Repo(MealsRemoteDataSourceInterface mealsRemoteDataSource){
        this.mealsRemoteDataSource = mealsRemoteDataSource;
    }

    public static RepoInterface getInstance(MealsRemoteDataSourceInterface mealsRemoteDataSource){
        if (instance == null){
            synchronized (Repo.class){
                if (instance == null){
                    instance = new Repo(mealsRemoteDataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public void getRandomMeal(RandomMealsPresenterInterface randomMealsPresenter){
        mealsRemoteDataSource.makeNetworkCall(randomMealsPresenter);
    }



}
