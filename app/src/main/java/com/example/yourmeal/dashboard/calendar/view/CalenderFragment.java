package com.example.yourmeal.dashboard.calendar.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.yourmeal.dashboard.Communicator;
import com.example.yourmeal.dashboard.calendar.presenter.CalendarPresenter;
import com.example.yourmeal.dashboard.calendar.presenter.CalendarPresenterInterface;
import com.example.yourmeal.dashboard.home.view.AllMealsAdapter;
import com.example.yourmeal.dashboard.home.view.OnMealItemClickListener;
import com.example.yourmeal.databinding.FragmentCalendarBinding;
import com.example.yourmeal.local.MealsLocalDataSource;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;

import java.util.Calendar;
import java.util.List;

public class CalenderFragment extends Fragment implements CalenderFragmentInterface, OnMealItemClickListener, OnDeleteClickListener {

    private FragmentCalendarBinding binding;
    private Communicator communicator;

    private CalendarPresenterInterface presenter;
    private CalendarAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        communicator = (Communicator) getActivity();
        presenter = new CalendarPresenter(
                Repo.getInstance(
                        new MealsRemoteDataSource(APIClient.getInstance().getService()),
                        new MealsLocalDataSource(requireContext())
                ),
                this
        );

        adapter = new CalendarAdapter(this, this);
        binding.recyclerView.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        binding.calendarView.setMinDate(calendar.getTimeInMillis());

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String formattedDate = day + "/" + (month + 1) + "/" + year;
                Toast.makeText(getActivity(), formattedDate, Toast.LENGTH_SHORT).show();
                presenter.getUpcomingMeals(formattedDate);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        communicator.showNavBottom();
    }

    @Override
    public void onStop() {
        super.onStop();
        communicator.hideNavBottom();
    }

    @Override
    public void setUpcomingMealList(List<Meal> upcomingMealList) {
        adapter.setMealsList(upcomingMealList);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClicked(Meal meal) {
        CalenderFragmentDirections.ActionNavigationCalenderToMealDetailsFragment action = CalenderFragmentDirections.actionNavigationCalenderToMealDetailsFragment(meal, null);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void onDeleteClicked(Meal meal) {
        presenter.deleteMeal(meal);
    }
}