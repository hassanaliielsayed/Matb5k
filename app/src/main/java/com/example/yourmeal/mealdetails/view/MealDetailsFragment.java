package com.example.yourmeal.mealdetails.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.OnNavigationListener;
import com.example.yourmeal.local.MealsLocalDataSource;
import com.example.yourmeal.mealdetails.presenter.MealsDetailsPresenter;
import com.example.yourmeal.mealdetails.presenter.MealsDetailsPresenterInterface;
import com.example.yourmeal.model.Ingredient;
import com.example.yourmeal.model.Meal;
import com.example.yourmeal.network.APIClient;
import com.example.yourmeal.network.MealsRemoteDataSource;
import com.example.yourmeal.repo.Repo;
import com.example.yourmeal.util.Constants;
import com.example.yourmeal.util.SharedPref;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;
import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface {

    ImageView imgMealThumb;
    TextView txtMealName, txtMealDetails, txtInstructions;
    ImageButton imgFavBtn, imgCalendarBtn;

    YouTubePlayerView youTubePlayerView;

    RecyclerView recyclerView;
    IngredientAdapter adapter;
    MealsDetailsPresenterInterface detailsPresenter;


    boolean isFav = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        Meal meal = args.getMeal();
        String id = args.getId();

        initComponent(view);

        detailsPresenter = new MealsDetailsPresenter(Repo.getInstance(
                new MealsRemoteDataSource(APIClient.getInstance().getService()),
                new MealsLocalDataSource(getContext())
        ), this);

        if (meal != null){
            setDetails(meal);
        } else if (id != null) {
            detailsPresenter.getMealByIdFromAPI(id);
        } else {
            Log.e("TAG", "onViewCreated: Meal and Id is equal null");
        }


    }

    private void isFavMeal(String idMeal) {
        String email = SharedPref.getInstance(getContext()).getStringValue(Constants.EMAIL, "");
        if (!email.isEmpty()){
            detailsPresenter.getMealById(idMeal, email);
        }
    }

    private void handleYouTubeLink(String youTubeURL) {
        if (youTubeURL == null) {
            Log.e("asd --> ", "YouTube URL is null");
        } else {
            String videoId = detailsPresenter.extractVideoId(youTubeURL);
            if (videoId == null) {
                Log.e("asd --> ", "Invalid YouTube URL or video ID");
            } else {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }

                    @Override
                    public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                        super.onError(youTubePlayer, error);
                        Log.d("asd --> ", "onError: " + error);
                    }
                });
            }
        }
    }

    private List<Ingredient> createIngredientList(Meal meal) {
        List<Ingredient> ingredientList = new ArrayList<>();

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient1(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient1()).concat(".png"),
                    meal.getStrMeasure1()
            ));
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient2(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient2()).concat(".png"),
                    meal.getStrMeasure2()
            ));
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient3(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient3()).concat(".png"),
                    meal.getStrMeasure3()
            ));
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient4(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient4()).concat(".png"),
                    meal.getStrMeasure4()
            ));
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient5(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient5()).concat(".png"),
                    meal.getStrMeasure5()
            ));
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient6(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient6()).concat(".png"),
                    meal.getStrMeasure6()
            ));
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient7(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient7()).concat(".png"),
                    meal.getStrMeasure7()
            ));
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient8(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient8()).concat(".png"),
                    meal.getStrMeasure8()
            ));
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient9(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient9()).concat(".png"),
                    meal.getStrMeasure9()
            ));
        }
        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient10(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient10()).concat(".png"),
                    meal.getStrMeasure10()
            ));
        }
        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient11(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient11()).concat(".png"),
                    meal.getStrMeasure11()
            ));
        }
        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient12(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient12()).concat(".png"),
                    meal.getStrMeasure12()
            ));
        }
        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient13(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient13()).concat(".png"),
                    meal.getStrMeasure13()
            ));
        }
        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient14(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient14()).concat(".png"),
                    meal.getStrMeasure14()
            ));
        }
        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient15(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient15()).concat(".png"),
                    meal.getStrMeasure15()
            ));
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient16(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient16()).concat(".png"),
                    meal.getStrMeasure16()
            ));
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient17(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient17()).concat(".png"),
                    meal.getStrMeasure17()
            ));
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient18(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient18()).concat(".png"),
                    meal.getStrMeasure18()
            ));
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient19(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient19()).concat(".png"),
                    meal.getStrMeasure19()
            ));
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
            ingredientList.add(new Ingredient(
                    meal.getStrIngredient20(),
                    "https://www.themealdb.com/images/ingredients/".concat(meal.getStrIngredient20()).concat(".png"),
                    meal.getStrMeasure20()
            ));
        }
        return ingredientList;
    }

    private void initComponent(View view){
        imgMealThumb = view.findViewById(R.id.imgMealThumb);
        txtMealName = view.findViewById(R.id.txtMealName);
        txtMealDetails = view.findViewById(R.id.txtMealDetails);
        txtInstructions = view.findViewById(R.id.txtInstructions);
        imgFavBtn = view.findViewById(R.id.imgFavAdd);
        imgCalendarBtn = view.findViewById(R.id.imgCalendarAdd);
        recyclerView = view.findViewById(R.id.recyclerView);
        youTubePlayerView = view.findViewById(R.id.you_tube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        adapter = new IngredientAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setDetails(Meal meal){

        Glide.with(this)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgMealThumb);

        txtMealName.setText(meal.getStrMeal());
        txtMealDetails.setText(
                getString(R.string.category).concat(meal.getStrCategory()).concat(" | ")
                        .concat(getString(R.string.area)).concat(meal.getStrArea()));

        txtInstructions.setText(meal.getStrInstructions());

        adapter.setIngredient(createIngredientList(meal));
        handleYouTubeLink(meal.getStrYoutube());

        isFavMeal(meal.getIdMeal());

        imgFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!SharedPref.getInstance(getContext()).getBooleanValue(Constants.ALREADY_LOGGED_IN, false)){

                    new AlertDialog.Builder(requireContext())
                            .setTitle("Authentication Required") // Title of the dialog
                            .setMessage("You are a gust, Would you like to login or create a new account") // Message
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    OnNavigationListener activity = (OnNavigationListener) getActivity();
                                    if (activity != null) {
                                        activity.navigateToAuthentication();
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Dismiss the dialog
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Set an icon
                            .show(); // Show the dialog

                } else if(isFav){
                    // popup to user
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Delete Item") // Title of the dialog
                            .setMessage("Are you sure you want to delete this item?") // Message
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Handle the delete action
                                    detailsPresenter.removeMealFromFav(meal);
                                    imgFavBtn.setImageResource(R.drawable.heart_empty);
                                    isFav = false;
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Dismiss the dialog
                                    dialogInterface.dismiss();
                                    isFav = true;
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Set an icon
                            .show(); // Show the dialog

                } else {
                    String email = SharedPref.getInstance(getContext()).getStringValue(Constants.EMAIL, "");
                    meal.setEmail(email);
                    detailsPresenter.addMealToFav(meal);
                    imgFavBtn.setImageResource(R.drawable.heart_filled);
                    isFav = true;

                }

            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        youTubePlayerView.release();
    }

    @Override
    public void showAddedMessage() {
        Toast.makeText(getContext(), "Added To Favorite Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRemovedMessage() {
        Toast.makeText(getContext(), "Removed Favorite Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFavItemIcon(Meal meal) {
        if (meal != null){
            imgFavBtn.setImageResource(R.drawable.heart_filled);
            isFav = true;
        } else {
            imgFavBtn.setImageResource(R.drawable.heart_empty);
            isFav = false;
        }
    }

    @Override
    public void onMealResponseSuccess(Meal meal) {
        setDetails(meal);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Toast.makeText(getContext(), "No Meals Found", Toast.LENGTH_SHORT).show();
    }
}