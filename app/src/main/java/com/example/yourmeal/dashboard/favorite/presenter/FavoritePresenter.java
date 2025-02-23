package com.example.yourmeal.dashboard.favorite.presenter;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.dashboard.favorite.view.FavouriteFragment;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.List;

public class FavoritePresenter implements FavoritePresenterInterface {

    FavouriteFragment view;

    RepoInterface repo;

    public FavoritePresenter(FavouriteFragment view, RepoInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void removeMeal(Meal meal) {
        repo.deleteMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getMealsLocally() {
        return repo.getStoredMeals();
    }

}
