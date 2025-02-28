package com.example.yourmeal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealIdResponse {
    @SerializedName("meals")
    private List<Meal> mealList;

    public List<Meal> getMeals() { return mealList; }
    public void setMeals(List<Meal> mealList) { this.mealList = mealList; }
}


