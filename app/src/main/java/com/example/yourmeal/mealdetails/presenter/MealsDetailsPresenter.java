package com.example.yourmeal.mealdetails.presenter;

import android.annotation.SuppressLint;

import com.example.yourmeal.mealdetails.view.MealDetailsViewInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

public class MealsDetailsPresenter implements MealsDetailsPresenterInterface {

    RepoInterface repo;
    MealDetailsViewInterface mealDetailsViewInterface;

    public MealsDetailsPresenter(RepoInterface repo, MealDetailsViewInterface mealDetailsViewInterface){
        this.repo = repo;
        this.mealDetailsViewInterface = mealDetailsViewInterface;
    }

    @Override
    public String extractVideoId(String youTubeURL) {
        /* *
         * ( -->
         * [ -->
         * ^ -->
         * & -->
         * ] -->
         * + -->
         * ) -->
         * */
        MatchResult matchResult = new Regex("v=([^&]+)").find(youTubeURL, 0);
        if (matchResult != null){
            return Objects.requireNonNull(matchResult.getGroups().get(1)).getValue();
        } else {
            return null;
        }
    }

    @Override
    public void addMealToFav(Meal meal) {
        repo.insertMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mealDetailsViewInterface.showAddedMessage();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMealById(String idMeal , String email) {

        repo.getMealById(idMeal, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                    mealDetailsViewInterface.showFavItemIcon(meal);
                });
    }

    @Override
    public void removeMealFromFav(Meal meal) {
        repo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mealDetailsViewInterface.showRemovedMessage();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getMealByIdFromAPI(String idMeal) {
        repo.getMealIdResponse(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((mealIdResponse, throwable) -> {
                    if (mealIdResponse != null){
                        mealDetailsViewInterface.onMealResponseSuccess(mealIdResponse.getMeals().get(0));
                    } else if (throwable != null) {
                        mealDetailsViewInterface.showErrorMessage(throwable.getMessage());
                    }
                });
    }


}
