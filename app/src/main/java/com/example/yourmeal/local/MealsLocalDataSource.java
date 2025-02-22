package com.example.yourmeal.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;

import java.util.List;

public class MealsLocalDataSource implements MealsLocalDataSourceInterface{

    MyDao myDao;

    public MealsLocalDataSource(Context context){
        MyDatabase databaseInstance = MyDatabase.getDatabaseInstance(context);
        myDao = databaseInstance.getMealDao();
    }

    @Override
    public void addMeal(Meal meal) {
        new Thread(() -> {
            myDao.insertMeal(meal);
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getMeals() {
        return myDao.getAllMeals();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(() -> {
            myDao.deleteMeal(meal);
        }).start();

    }
}
