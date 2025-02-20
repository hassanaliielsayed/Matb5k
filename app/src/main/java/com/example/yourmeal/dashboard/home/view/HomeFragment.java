package com.example.yourmeal.dashboard.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenter;
import com.example.yourmeal.dashboard.home.presenter.RandomMealsPresenterInterface;
import com.example.yourmeal.databinding.FragmentHomeBinding;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;

public class HomeFragment extends Fragment implements HomeViewInterface{

    RandomMealsPresenterInterface randomMealsPresenter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        randomMealsPresenter = new RandomMealsPresenter(
                Repo.getInstance(
                        new MealsRemoteDataSource(APIClient.getInstance().getService())
                ),
                this
        );
        randomMealsPresenter.getRandomMeal();
    }

    @Override
    public void onRandomMealResponseSuccess(Meal meal) {
        binding.progressBarMeal.setVisibility(View.GONE);
        binding.txtTitle.setText(meal.getStrMeal());
        binding.txtArea.setText(getString(R.string.area).concat(meal.getStrArea()));
        binding.txtCategory.setText(getString(R.string.category).concat(meal.getStrCategory()));
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imgCard);
    }

    @Override
    public void onResponseError(String errorMsg) {
        binding.progressBarMeal.setVisibility(View.GONE);
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}