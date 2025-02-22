package com.example.yourmeal.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yourmeal.model.Meal;

import java.util.List;

@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);
    @Delete
    void deleteMeal(Meal meal);
    @Query("select * from my_meal_table")
    LiveData<List<Meal>> getAllMeals();

}
