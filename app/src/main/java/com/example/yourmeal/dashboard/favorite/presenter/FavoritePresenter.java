package com.example.yourmeal.dashboard.favorite.presenter;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.favorite.view.FavouriteFragment;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter implements FavoritePresenterInterface {

    FavouriteFragment view;

    RepoInterface repo;

    public FavoritePresenter(FavouriteFragment view, RepoInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void removeMeal(Meal meal) {
        repo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        view.ShowMessage();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMealsLocally() {
        repo.getStoredMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealList -> {
                    view.showProductsLocally(mealList);
                });
    }

}
