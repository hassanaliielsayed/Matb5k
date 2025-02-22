package com.example.yourmeal.mealdetails.presenter;

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
    public void addProductToFav(Meal meal) {
        repo.insertMeal(meal);
    }

}
