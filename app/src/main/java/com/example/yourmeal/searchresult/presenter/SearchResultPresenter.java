package com.example.yourmeal.searchresult.presenter;

import android.annotation.SuppressLint;

import com.example.yourmeal.repo.RepoInterface;
import com.example.yourmeal.searchresult.view.SearchResultView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchResultPresenter implements SearchResultPresenterInterface {

    RepoInterface repo;

    SearchResultView view;

    public SearchResultPresenter(RepoInterface repo, SearchResultView view) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCountryMeals(String countryName) {
        repo.getCountryMeals(countryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((countryMealsResponse, throwable) -> {
                    if (countryMealsResponse != null) {
                        view.onSearchResultResponseSuccess(countryMealsResponse.getMeals());
                    } else if (throwable != null) {
                        view.onResponseError(throwable.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getIngredientsMeals(String ingredientName) {
        repo.getIngredientMeals(ingredientName)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ingredientMealsResponse, throwable) -> {
                   if (ingredientMealsResponse != null){
                       view.onSearchResultResponseSuccess(ingredientMealsResponse.getMeals());
                   } else if (throwable != null) {
                       view.onResponseError(throwable.getMessage());
                   }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCategoryMeals(String categoryName) {
        repo.getCategoryMeals(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((categoryMealsResponse, throwable) -> {
                    if (categoryMealsResponse != null){
                        view.onSearchResultResponseSuccess(categoryMealsResponse.getMeals());
                    } else if (throwable != null) {
                        view.onResponseError(throwable.getMessage());
                    }
                });
    }
}
