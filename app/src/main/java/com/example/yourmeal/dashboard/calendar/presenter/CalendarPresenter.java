package com.example.yourmeal.dashboard.calendar.presenter;

import android.annotation.SuppressLint;

import com.example.yourmeal.dashboard.calendar.view.CalenderFragmentInterface;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter implements CalendarPresenterInterface {

    private RepoInterface repo;

    private CalenderFragmentInterface view;

    public CalendarPresenter(RepoInterface repo, CalenderFragmentInterface view) {
        this.repo = repo;
        this.view = view;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getUpcomingMeals(String selectedDate) {
        repo.getUpcomingMeals(selectedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealList -> {
                    view.setUpcomingMealList(mealList);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMeal(Meal meal) {
        repo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.showMessage("Deleted Successfully");
                });
    }
}
