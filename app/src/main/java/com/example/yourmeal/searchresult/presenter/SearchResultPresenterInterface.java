package com.example.yourmeal.searchresult.presenter;

import com.example.yourmeal.model.CategoryMealsResponse;

import io.reactivex.rxjava3.core.Single;

public interface SearchResultPresenterInterface {

    void getCountryMeals(String countryName);

    void getIngredientsMeals(String ingredientName);

    void getCategoryMeals(String categoryName);
}
