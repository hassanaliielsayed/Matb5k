package com.example.yourmeal.dashboard.favorite.view;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface OnRemoveClicked {

    void removeItem(Meal meal);

    void ShowMessage();

    void showProductsLocally(List<Meal> mealList);
}
