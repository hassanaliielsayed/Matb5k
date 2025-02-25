package com.example.yourmeal.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSource implements MealsLocalDataSourceInterface{

    MyDao myDao;

    public MealsLocalDataSource(Context context){
        MyDatabase databaseInstance = MyDatabase.getDatabaseInstance(context);
        myDao = databaseInstance.getMealDao();
    }

    @Override
    public Completable addMeal(Meal meal) {

        return myDao.insertMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getMeals() {
        return myDao.getAllMeals();
    }

    @Override
    public Completable removeMeal(Meal meal) {

        return myDao.deleteMeal(meal);
    }

    @Override
    public Flowable<Meal> getMealById(String idMeal) {
        return myDao.getMealById(idMeal);
    }
}
