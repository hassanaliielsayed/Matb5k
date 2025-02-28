package com.example.yourmeal.dashboard.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.yourmeal.R;
import com.example.yourmeal.dashboard.Communicator;
import com.example.yourmeal.dashboard.DashboardActivity;
import com.example.yourmeal.dashboard.OnNavigationListener;
import com.example.yourmeal.util.Constants;
import com.example.yourmeal.util.SharedPref;
import com.example.yourmeal.util.SharedUIMethods;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    TextView txtName;
    ConstraintLayout constraintLayoutFav, constraintLayoutCal;

    Button btnLogout;

    private Communicator communicator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = view.findViewById(R.id.txtName);
        btnLogout = view.findViewById(R.id.btnLogout);
        constraintLayoutFav = view.findViewById(R.id.constraintLayoutFav);
        constraintLayoutCal = view.findViewById(R.id.constraintLayoutCal);
        String userName = SharedPref.getInstance(getContext()).getStringValue(Constants.USER_NAME, "");
        if (userName.isEmpty()){
            txtName.setText(SharedPref.getInstance(getContext()).getStringValue(Constants.EMAIL, ""));
        } else {
            txtName.setText(userName);
        }

        constraintLayoutFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((DashboardActivity) getActivity()).binding.navView.setSelectedItemId(R.id.navigation_fav);

                //Navigation.findNavController(view).navigate(R.id.action_navigation_profile_to_navigation_fav);
            }
        });

        constraintLayoutCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DashboardActivity) getActivity()).binding.navView.setSelectedItemId(R.id.navigation_calender);
                //Navigation.findNavController(view).navigate(R.id.action_navigation_profile_to_navigation_calender);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedUIMethods.saveUserInSharedPreference(requireContext(), "", "", "", false);
                FirebaseAuth.getInstance().signOut();
                OnNavigationListener activity = (OnNavigationListener) getActivity();
                if (activity != null) {
                    activity.navigateToAuthentication();
                }
            }
        });

        communicator = (Communicator) getActivity();
    }

    public void onResume() {
        super.onResume();
        communicator.showNavBottom();
    }

    @Override
    public void onStop() {
        super.onStop();
        communicator.hideNavBottom();
    }
}