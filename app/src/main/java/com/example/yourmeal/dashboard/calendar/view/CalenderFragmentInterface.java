package com.example.yourmeal.dashboard.calendar.view;

import com.example.yourmeal.model.Meal;

import java.util.List;

public interface CalenderFragmentInterface {

    void setUpcomingMealList(List<Meal> upcomingMealList);

    void showMessage(String msg);
}
