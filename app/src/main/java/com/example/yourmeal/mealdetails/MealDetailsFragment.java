package com.example.yourmeal.mealdetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.model.Meal;

public class MealDetailsFragment extends Fragment {



    ImageView imgMealThumb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        Meal meal = args.getMeal();
        Log.d("TAG", "Received username: " + meal.getStrMeal());

        imgMealThumb = view.findViewById(R.id.imgMealThumb);
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgMealThumb);

    }
}