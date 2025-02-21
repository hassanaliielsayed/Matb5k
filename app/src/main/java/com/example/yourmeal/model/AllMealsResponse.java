package com.example.yourmeal.model;

import java.util.List;

public class AllMealsResponse {

    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
