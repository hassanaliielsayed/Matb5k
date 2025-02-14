package com.example.yourmeal.onboarding;

import static com.example.yourmeal.util.Constants.IS_FIRST_TIME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.yourmeal.R;
import com.example.yourmeal.auth.AuthActivity;
import com.example.yourmeal.util.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    List<ViewPagerItem> viewPagerItemsList;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(view -> {
            SharedPref.getInstance(this).putValue(IS_FIRST_TIME, false);
            startActivity(new Intent(this, AuthActivity.class));
        });

        viewPager2 = findViewById(R.id.viewMapper);
        int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four};
        String[] headings = {"Baked", "For You", "Welcome", "Matb5k"};


        viewPagerItemsList = new ArrayList<>();

        for (int i = 0; i < images.length; i++){
            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], headings[i]);
            viewPagerItemsList.add(viewPagerItem);
        }

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemsList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(ViewPager2.OVER_SCROLL_NEVER);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}