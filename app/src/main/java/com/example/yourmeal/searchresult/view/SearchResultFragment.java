package com.example.yourmeal.searchresult.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourmeal.R;
import com.example.yourmeal.local.MealsLocalDataSource;
import com.example.yourmeal.model.FilterMeals;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;
import com.example.yourmeal.searchresult.presenter.SearchResultPresenter;
import com.example.yourmeal.searchresult.presenter.SearchResultPresenterInterface;
import com.example.yourmeal.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment implements SearchResultView, OnMealClickListener {

    TextView txtComing;
    RecyclerView recyclerView;
    SearchResultAdapter adapter;
    List<FilterMeals> filterMealsList;


    SearchResultPresenterInterface searchResultPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchResultFragmentArgs args = SearchResultFragmentArgs.fromBundle(getArguments());
        int checkedChip = args.getCheckedChip();
        String itemName = args.getItemName();

        txtComing = view.findViewById(R.id.txtComing);
        txtComing.setText(itemName + " Meals");
        filterMealsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new SearchResultAdapter(this);


        searchResultPresenter = new SearchResultPresenter(
                Repo.getInstance(
                        new MealsRemoteDataSource(APIClient.getInstance().getService()),
                        new MealsLocalDataSource(getContext())

                ),
                this
        );

        if (checkedChip == Constants.COUNTRY){
            searchResultPresenter.getCountryMeals(itemName);
            recyclerView.setAdapter(adapter);
        } else if (checkedChip == Constants.INGREDIENT) {
            searchResultPresenter.getIngredientsMeals(itemName);
            recyclerView.setAdapter(adapter);
        } else if (checkedChip == Constants.CATEGORY) {
            searchResultPresenter.getCategoryMeals(itemName);
            recyclerView.setAdapter(adapter);
        }


    }

    @Override
    public void onSearchResultResponseSuccess(List<FilterMeals> filterMeals) {
        adapter.setData(filterMeals);
    }




    @Override
    public void onResponseError(String errorMsg) {
        Toast.makeText(getContext(), "No Meals Found", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMealClicked(String idMeal) {
        SearchResultFragmentDirections.ActionSearchResultFragmentToMealDetailsFragment action = SearchResultFragmentDirections.actionSearchResultFragmentToMealDetailsFragment(null, idMeal);
        Navigation.findNavController(requireView()).navigate(action);
    }
}