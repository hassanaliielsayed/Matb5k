package com.example.yourmeal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientMealsResponse {
    @SerializedName("meals")
    private List<FilterMeals> filterMealsList;

    public List<FilterMeals> getMeals() { return filterMealsList; }
    public void setMeals(List<FilterMeals> value) { this.filterMealsList = value; }
}


