package com.example.yourmeal.dashboard.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yourmeal.dashboard.Communicator;
import com.example.yourmeal.databinding.FragmentNotificationsBinding;

public class FavFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private Communicator communicator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}