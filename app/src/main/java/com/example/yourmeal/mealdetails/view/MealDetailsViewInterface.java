package com.example.yourmeal.mealdetails.view;

import com.example.yourmeal.model.Meal;

public interface MealDetailsViewInterface {
    void showRemovedMessage();

    void showFavItemIcon(Meal meal);

    void onMealResponseSuccess(Meal meal);

    void showErrorMessage(String errorMsg);

    void showResultMessage(String msg);

}
