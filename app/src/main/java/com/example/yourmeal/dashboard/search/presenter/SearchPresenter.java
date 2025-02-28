package com.example.yourmeal.dashboard.search.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.yourmeal.dashboard.search.view.SearchFragmentViewInterface;
import com.example.yourmeal.repo.RepoInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter implements SearchPresenterInterface {

    SearchFragmentViewInterface view;

    RepoInterface repo;

    public SearchPresenter(SearchFragmentViewInterface view, RepoInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMealsAreas() {
        repo.getAllMealsAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((allAreasResponse, throwable) -> {
                    if (allAreasResponse != null){
                        view.onMealsAreaResponseSuccess(allAreasResponse.getAreaMealList());
                    } else if (throwable != null) {
                        view.onResponseError(throwable.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllCategories() {
        repo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((allCategoriesResponse, throwable) -> {
                    if (allCategoriesResponse != null){
                        Log.i("asd -->", "getAllCategories: Presenter: " + allCategoriesResponse.getCategories());
                        view.onCategoryResponseSuccess(allCategoriesResponse.getCategories());
                    } else if (throwable != null) {
                        view.onResponseError(throwable.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllIngredients() {
        repo.getAllIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((allIngredientsResponse, throwable) -> {
                   if (allIngredientsResponse != null){
                       view.onIngredientsResponseSuccess(allIngredientsResponse.getIngredientsAPI());
                   } else if (throwable != null){
                       view.onResponseError(throwable.getMessage());
                   }
                });
    }

}
