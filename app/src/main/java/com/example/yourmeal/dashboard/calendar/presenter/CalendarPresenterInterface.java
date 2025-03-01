package com.example.yourmeal.dashboard.calendar.presenter;

import com.example.yourmeal.model.Meal;

public interface CalendarPresenterInterface {

    void getUpcomingMeals(String selectedDate);

    void deleteMeal(Meal meal);
}
