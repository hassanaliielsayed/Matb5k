package com.example.yourmeal.searchresult.view;

import com.example.yourmeal.model.FilterMeals;

import java.util.List;

public interface SearchResultView {

    void onSearchResultResponseSuccess(List<FilterMeals> countryMeals);

    void onResponseError(String errorMsg);
}
