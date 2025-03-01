package com.example.yourmeal.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
    @Query("select * from my_meal_table where email = :email")
    Flowable<List<Meal>> getAllMeals(String email);

    @Query("select * from my_meal_table where idMeal = :idMeal and email = :email")
    Flowable<Meal> getMealById(String idMeal, String email);

    @Update
    Completable updateMeal(Meal meal);

    @Query("select * from my_meal_table where upComingDate = :selectedDate")
    Flowable<List<Meal>> getUpcomingMeals(String selectedDate);






}
