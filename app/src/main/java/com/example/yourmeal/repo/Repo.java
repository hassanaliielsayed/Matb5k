package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.local.MealsLocalDataSourceInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.MealsRemoteDataSourceInterface;

import java.util.List;

public class Repo implements RepoInterface {

    private volatile static RepoInterface instance = null;
    private MealsRemoteDataSourceInterface mealsRemoteDataSource;
    private MealsLocalDataSourceInterface mealsLocalDataSource;

    private Repo(MealsRemoteDataSourceInterface mealsRemoteDataSource, MealsLocalDataSourceInterface mealsLocalDataSource){
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public static RepoInterface getInstance(MealsRemoteDataSourceInterface mealsRemoteDataSource, MealsLocalDataSourceInterface mealsLocalDataSource){
        if (instance == null){
            synchronized (Repo.class){
                if (instance == null){
                    instance = new Repo(mealsRemoteDataSource, mealsLocalDataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public void getRandomMeal(HomePresenterInterface randomMealsPresenter){
        mealsRemoteDataSource.makeRandomMealNetworkCall(randomMealsPresenter);
    }

    @Override
    public void getAllMeals(HomePresenterInterface randomMealsPresenter, char character) {
        mealsRemoteDataSource.makeAllMealsNetworkCall(randomMealsPresenter, character);
    }

    @Override
    public void insertMeal(Meal meal) {
        mealsLocalDataSource.addMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return (mealsLocalDataSource.getMeals());
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealsLocalDataSource.removeMeal(meal);
    }

    @Override
    public LiveData<Meal> getMealById(String idMeal) {
        return (mealsLocalDataSource.getMealById(idMeal));
    }


}
