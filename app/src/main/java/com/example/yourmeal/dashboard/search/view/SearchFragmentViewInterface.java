package com.example.yourmeal.dashboard.search.view;

import com.example.yourmeal.model.AreaMeal;
import com.example.yourmeal.model.Category;
import com.example.yourmeal.model.IngredientsAPI;
import com.example.yourmeal.model.Meal;

import java.util.List;

public interface SearchFragmentViewInterface {
    void onMealsAreaResponseSuccess(List<AreaMeal> areaMealsList);

    void onCategoryResponseSuccess(List<Category> categoryList);

    void onIngredientsResponseSuccess(List<IngredientsAPI> ingredientsAPIList);

    void onResponseError(String errorMsg);
}
