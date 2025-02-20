package com.example.yourmeal.auth.login;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yourmeal.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {

    TextInputEditText txtInputPassword, txtInputEmail, txtInputPassETGmail;
    MaterialButton btnLogin;
    ProgressBar progressBar;
    TextView txtCreateAccount;

    FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtInputEmail = view.findViewById(R.id.txtInputEmailETLogin);
        txtInputPassword = view.findViewById(R.id.txtInputPassETLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        progressBar = view.findViewById(R.id.progress_bar_login);
        txtCreateAccount = view.findViewById(R.id.txtCreateAccount);
        txtInputPassETGmail = view.findViewById(R.id.txtInputPassETGmail);

        btnLogin.setOnClickListener(view1 -> {
            loginUser();
        });

        txtCreateAccount.setOnClickListener(view2 -> {
            Navigation.findNavController(view).popBackStack();
        });



    }

    private void loginUser() {
        String email = Objects.requireNonNull(txtInputEmail.getText()).toString();
        String password = Objects.requireNonNull(txtInputPassword.getText()).toString();

        boolean isValidateData = validateData(email, password);
        if (isValidateData){
            loginInFirebase(email, password);
        }
    }

    private void loginInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            changeProgress(false);
            if (task.isSuccessful()){
                // TODO: go to Home
            } else {
                Toast.makeText(getActivity(), Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeProgress(boolean inProgress){
        if (inProgress){
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateData(String email, String password){
        boolean result = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtInputEmail.setError("Email is Invalid");
            result = false;
        }
        if (password.length() < 8){
            txtInputPassword.setError("Password is Invalid");
            result = false;
        }

        return result;
    }
}