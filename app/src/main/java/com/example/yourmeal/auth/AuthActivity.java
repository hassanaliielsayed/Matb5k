package com.example.yourmeal.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.DashboardActivity;

public class AuthActivity extends AppCompatActivity implements OnDashboardNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    @Override
    public void navigateToDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
}