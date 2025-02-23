package com.example.yourmeal.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.yourmeal.model.Meal;

@Database(entities = {Meal.class}, version = 1)
@TypeConverters({ObjectConverter.class})
public abstract class MyDatabase extends RoomDatabase {

    public volatile static MyDatabase instance;
    public abstract MyDao getMealDao();

    public static MyDatabase getDatabaseInstance(Context context){
        if (instance == null){
            synchronized (MyDatabase.class){
                if (instance == null){
                    instance = Room
                            .databaseBuilder(context, MyDatabase.class, "mealdb")
                            .build();
                }
            }
        }
        return instance;
    }
}
