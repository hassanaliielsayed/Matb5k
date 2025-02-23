package com.example.yourmeal.mealdetails.presenter;

import androidx.lifecycle.LiveData;

import com.example.yourmeal.model.Meal;
import com.example.yourmeal.repo.RepoInterface;

import java.util.Objects;

import kotlin.text.MatchResult;
import kotlin.text.Regex;

public class MealsDetailsPresenter implements MealsDetailsPresenterInterface {

    RepoInterface repo;

    public MealsDetailsPresenter(RepoInterface repo){
        this.repo = repo;
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
        repo.insertMeal(meal);
    }

    @Override
    public LiveData<Meal> getMealById(String idMeal) {
        return repo.getMealById(idMeal);
    }

    @Override
    public void removeMealFromFav(Meal meal) {
        repo.deleteMeal(meal);
    }

}
