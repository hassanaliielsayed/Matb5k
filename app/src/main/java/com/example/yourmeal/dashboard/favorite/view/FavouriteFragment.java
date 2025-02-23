package com.example.yourmeal.dashboard.favorite.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yourmeal.dashboard.Communicator;
import com.example.yourmeal.dashboard.favorite.presenter.FavoritePresenter;
import com.example.yourmeal.dashboard.favorite.presenter.FavoritePresenterInterface;
import com.example.yourmeal.dashboard.home.view.OnMealItemClickListener;
import com.example.yourmeal.databinding.FragmentFavouriteBinding;
import com.example.yourmeal.local.MealsLocalDataSource;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;

import java.util.List;

public class FavouriteFragment extends Fragment implements OnItemClickListener, OnRemoveClicked {

    FavoriteAdapter adapter;

    FavoritePresenterInterface favPresenter;
    LiveData<List<Meal>> mealsLocally;

    private FragmentFavouriteBinding binding;
    private Communicator communicator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new FavoriteAdapter(this, this);

        binding.recyclerViewFav.setAdapter(adapter);


        favPresenter = new FavoritePresenter(this, Repo.getInstance(
                new MealsRemoteDataSource(APIClient.getInstance().getService()),
                new MealsLocalDataSource(getContext())));

        mealsLocally = favPresenter.getMealsLocally();
        mealsLocally.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealList) {
                if (mealList == null){
                    Log.i("asd --> ", "onChanged: " + mealList.size());
                } else {
                    Log.i("asd --> ", "onChanged: " + mealList.size());
                    adapter.setMealsList(mealList);
                }

            }
        });


        communicator = (Communicator) getActivity();


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
    public void onMealClicked(Meal meal) {
        FavouriteFragmentDirections.ActionNavigationFavToMealDetailsFragment action = FavouriteFragmentDirections.actionNavigationFavToMealDetailsFragment(meal);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void removeItem(Meal meal) {
        favPresenter.removeMeal(meal);
    }
}