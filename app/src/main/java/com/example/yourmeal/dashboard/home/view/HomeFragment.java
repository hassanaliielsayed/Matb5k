package com.example.yourmeal.dashboard.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.home.presenter.HomePresenter;
import com.example.yourmeal.dashboard.home.presenter.HomePresenterInterface;
import com.example.yourmeal.databinding.FragmentHomeBinding;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;

import java.util.List;

public class HomeFragment extends Fragment implements HomeViewInterface, OnMealItemClickListener{

    HomePresenterInterface randomMealsPresenter;

    AllMealsAdapter adapter;
    RecyclerView recyclerView;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new AllMealsAdapter(this);
        binding.allMealRecyclerView.setAdapter(adapter);

        randomMealsPresenter = new HomePresenter(
                Repo.getInstance(
                        new MealsRemoteDataSource(APIClient.getInstance().getService())
                ),
                this
        );
        randomMealsPresenter.getRandomMeal();
        randomMealsPresenter.getAllMeals();
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

    @Override
    public void onAllMealsResponseSuccess(List<Meal> mealsList) {
        if (mealsList == null){
            Log.i("asd --> ", "onAllMealsResponseSuccess: NULL");
        } else {
            Log.d("asd --> ", "onAllMealsResponseSuccess: " + mealsList.toString());
            adapter.setMealsList(mealsList);
        }
    }

    @Override
    public void onMealClicked(Meal meal) {

        HomeFragmentDirections.ActionNavigationHomeToMealDetailsFragment action = HomeFragmentDirections.actionNavigationHomeToMealDetailsFragment(meal);
        Navigation.findNavController(requireView()).navigate(action);
    }
}