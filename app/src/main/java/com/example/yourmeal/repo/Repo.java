package com.example.yourmeal.repo;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.local.MealsLocalDataSourceInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.model.RandomMealResponse;
import com.example.yourmeal.network.MealsRemoteDataSourceInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

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
    public Single<RandomMealResponse> getRandomMeal(){
        return mealsRemoteDataSource.makeRandomMealNetworkCall();
    }

    @Override
    public Single<RandomMealResponse> getAllMeals(char character) {
        return mealsRemoteDataSource.makeAllMealsNetworkCall(character);
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return mealsLocalDataSource.addMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return (mealsLocalDataSource.getMeals());
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return mealsLocalDataSource.removeMeal(meal);
    }

    @Override
    public Flowable<Meal> getMealById(String idMeal) {
        return (mealsLocalDataSource.getMealById(idMeal));
    }


}
