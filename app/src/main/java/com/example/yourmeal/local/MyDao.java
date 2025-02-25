package com.example.yourmeal.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yourmeal.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(Meal meal);
    @Delete
    Completable deleteMeal(Meal meal);
    @Query("select * from my_meal_table")
    Flowable<List<Meal>> getAllMeals();

    @Query("select * from my_meal_table where idMeal = :idMeal")
    Flowable<Meal> getMealById(String idMeal);




}
