package com.example.yourmeal.dashboard;

import android.os.Bundle;
import android.view.View;

import com.example.yourmeal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.yourmeal.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity implements Communicator {

    private ActivityDashboardBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calender, R.id.navigation_fav,
                R.id.navigation_search, R.id.navigation_profile
        ).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    @Override
    public void hideNavBottom() {
        binding.navView.setVisibility(View.GONE);
    }

    @Override
    public void showNavBottom() {
        binding.navView.setVisibility(View.VISIBLE);
    }
}