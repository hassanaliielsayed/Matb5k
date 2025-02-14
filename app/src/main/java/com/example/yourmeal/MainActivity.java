package com.example.yourmeal;

import static com.example.yourmeal.util.Constants.IS_FIRST_TIME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.yourmeal.auth.AuthActivity;
import com.example.yourmeal.onboarding.OnboardingActivity;
import com.example.yourmeal.util.SharedPref;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boolean isFirstTime = SharedPref.getInstance(this).getBooleanValue(IS_FIRST_TIME, true);

        new Handler(Looper.getMainLooper()).postDelayed(()->{
            if (isFirstTime){
                startActivity(new Intent(this, OnboardingActivity.class));

            } else {
                startActivity(new Intent(this, AuthActivity.class));
            }
            finish();
        },6000L);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}