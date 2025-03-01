package com.example.yourmeal.dashboard.search.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.example.yourmeal.util.ConnectionLiveData;
import com.example.yourmeal.util.Constants;
import com.google.android.material.chip.Chip;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.Communicator;
import com.example.yourmeal.dashboard.search.presenter.SearchPresenter;
import com.example.yourmeal.dashboard.search.presenter.SearchPresenterInterface;
import com.example.yourmeal.local.MealsLocalDataSource;
import com.example.yourmeal.model.AreaMeal;
import com.example.yourmeal.model.Category;
import com.example.yourmeal.model.IngredientsAPI;
import com.example.yourmeal.model.SearchItem;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SearchFragment extends Fragment implements SearchFragmentViewInterface, OnItemSearchClickInterface {

    List<SearchItem> searchItemsList;

    SearchPresenterInterface searchPresenter;
    private SearchFragmentAdapter adapter;
    private RecyclerView recyclerViewSearch;

    int checkedChip = Constants.CATEGORY;

    LottieAnimationView lotti;

    EditText edtSearch;
    private static final Map<String, String> countryCodeMap = new HashMap<>();

    static {
        countryCodeMap.put("American", "us");
        countryCodeMap.put("British", "gb");
        countryCodeMap.put("Canadian", "ca");
        countryCodeMap.put("Chinese", "cn");
        countryCodeMap.put("Croatian", "hr");
        countryCodeMap.put("Dutch", "nl");
        countryCodeMap.put("Egyptian", "eg");
        countryCodeMap.put("Filipino", "ph");
        countryCodeMap.put("French", "fr");
        countryCodeMap.put("Greek", "gr");
        countryCodeMap.put("Indian", "in");
        countryCodeMap.put("Irish", "ie");
        countryCodeMap.put("Italian", "it");
        countryCodeMap.put("Jamaican", "jm");
        countryCodeMap.put("Japanese", "jp");
        countryCodeMap.put("Kenyan", "ke");
        countryCodeMap.put("Malaysian", "my");
        countryCodeMap.put("Mexican", "mx");
        countryCodeMap.put("Moroccan", "ma");
        countryCodeMap.put("Norwegian", "no");
        countryCodeMap.put("Polish", "pl");
        countryCodeMap.put("Portuguese", "pt");
        countryCodeMap.put("Russian", "ru");
        countryCodeMap.put("Spanish", "es");
        countryCodeMap.put("Thai", "th");
        countryCodeMap.put("Tunisian", "tn");
        countryCodeMap.put("Turkish", "tr");
        countryCodeMap.put("Ukrainian", "ua");
        countryCodeMap.put("Uruguayan", "uy");
        countryCodeMap.put("Vietnamese", "vn");
    }

    private ChipGroup chipGroup;


    private Communicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        communicator = (Communicator) getActivity();
        searchItemsList = new ArrayList<>();
        adapter = new SearchFragmentAdapter(this);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        recyclerViewSearch.setAdapter(adapter);
        chipGroup = view.findViewById(R.id.chipGroup);
        edtSearch = view.findViewById(R.id.edtSearch);
        lotti = view.findViewById(R.id.lotti);



        searchPresenter = new SearchPresenter(
                this,
                Repo.getInstance(
                        new MealsRemoteDataSource(APIClient.getInstance().getService()),
                        new MealsLocalDataSource(getContext())
                )
        );

        new ConnectionLiveData(requireContext()).observe(getViewLifecycleOwner(), isNetworkConnected -> {
            if (isNetworkConnected){

                edtSearch.setVisibility(View.VISIBLE);
                chipGroup.setVisibility(View.VISIBLE);
                recyclerViewSearch.setVisibility(View.VISIBLE);
                lotti.setVisibility(View.GONE);
                chipGroup.setOnCheckedChangeListener((group, checkedId) -> {


                    if (checkedId == R.id.chipCategory){
                        checkedChip = Constants.CATEGORY;
                        searchPresenter.getAllCategories();
                    } else if (checkedId == R.id.chipCountry) {
                        checkedChip = Constants.COUNTRY;
                        searchPresenter.getAllMealsAreas();
                    } else if (checkedId == R.id.chipIngredient) {
                        checkedChip = Constants.INGREDIENT;
                        searchPresenter.getAllIngredients();
                    }



                });

            } else {

                edtSearch.setVisibility(View.GONE);
                chipGroup.setVisibility(View.GONE);
                recyclerViewSearch.setVisibility(View.GONE);
                lotti.setVisibility(View.VISIBLE);


            }
        });

//        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
//
//            if (checkedId == R.id.chipCategory){
//                checkedChip = Constants.CATEGORY;
//                searchPresenter.getAllCategories();
//            } else if (checkedId == R.id.chipCountry) {
//                checkedChip = Constants.COUNTRY;
//                searchPresenter.getAllMealsAreas();
//            } else if (checkedId == R.id.chipIngredient) {
//                checkedChip = Constants.INGREDIENT;
//                searchPresenter.getAllIngredients();
//            }
//
//        });

        if (chipGroup.getChildCount() > 0) {
            Chip firstChip = (Chip) chipGroup.getChildAt(0);
            if (firstChip != null){
                chipGroup.check(firstChip.getId());
            }
        }

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<SearchItem> result = searchItemsList
                        .stream()
                        .filter(s -> s.getName().contains(edtSearch.getText().toString()))
                        .collect(Collectors.toList());
                adapter.setSearchItemList(result);
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
    public void onMealsAreaResponseSuccess(List<AreaMeal> areaMealsList) {
        String name, imgURL, countryCode;
        searchItemsList.clear();
        if (areaMealsList != null){
            Log.i("asd -->", "onIngredientsResponseSuccess: " + areaMealsList.get(0));
            for (AreaMeal item : areaMealsList){
                name = item.getStrArea();
                countryCode = countryCodeMap.get(name);
                imgURL = "https://www.themealdb.com/images/icons/flags/big/64/" + countryCode + ".png";
                SearchItem searchItem = new SearchItem(name, imgURL);
                searchItemsList.add(searchItem);

            }
            adapter.setSearchItemList(searchItemsList);

        } else {
            Log.i("asd -->", "onMealsAreaResponseSuccess: null");
        }

    }

    @Override
    public void onCategoryResponseSuccess(List<Category> categoryList) {
        String name, imgURL;
        searchItemsList.clear();
        if (categoryList != null){
            Log.i("asd --> ", "onCategoryResponseSuccess: " + categoryList.get(0));
            for (Category item : categoryList){
                name = item.getStrCategory();
                imgURL = "https://www.themealdb.com/images/category/" + name + ".png";
                SearchItem searchItem = new SearchItem(name, imgURL);
                searchItemsList.add(searchItem);

            }
            adapter.setSearchItemList(searchItemsList);

        } else {
            Log.i("asd --> ", "categoryList == null");
        }

    }

    @Override
    public void onIngredientsResponseSuccess(List<IngredientsAPI> ingredientsAPIList) {
        String name, imgURL;
        searchItemsList.clear();
        if (ingredientsAPIList != null){
            Log.i("asd -->", "onIngredientsResponseSuccess: " + ingredientsAPIList.get(0));
            for (IngredientsAPI item : ingredientsAPIList){
                name = item.getStrIngredient();
                imgURL = "https://www.themealdb.com/images/ingredients/" + name + ".png";
                SearchItem searchItem = new SearchItem(name, imgURL);
                searchItemsList.add(searchItem);

            }
            adapter.setSearchItemList(searchItemsList);

        } else {
            Log.i("asd --> ", "onIngredientsResponseSuccess: null");
        }

    }

    @Override
    public void onResponseError(String errorMsg) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(String value) {
        SearchFragmentDirections.ActionNavigationSearchToSearchResultFragment action = SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(value, checkedChip);
        Navigation.findNavController(requireView()).navigate(action);
    }
}