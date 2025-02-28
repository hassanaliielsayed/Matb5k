package com.example.yourmeal.auth.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.example.yourmeal.auth.OnDashboardNavigationListener;
import com.example.yourmeal.util.SharedUIMethods;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginFragment extends Fragment {

    TextInputEditText txtInputPassword, txtInputEmail, txtInputPassETGmail;
    MaterialButton btnLogin;
    ProgressBar progressBar;
    TextView txtCreateAccount, txtGuest;
    ConstraintLayout constraintLayoutGoogle;
    private GoogleSignInClient client;




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
        btnLogin = view.findViewById(R.id.btnLogout);
        progressBar = view.findViewById(R.id.progress_bar_login);
        txtCreateAccount = view.findViewById(R.id.txtCreateAccount);
        constraintLayoutGoogle = view.findViewById(R.id.constraintLayoutGoogle);
        txtGuest = view.findViewById(R.id.txtGuest);

        txtGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnDashboardNavigationListener activity = (OnDashboardNavigationListener) getActivity();
                if (activity != null) {
                    activity.navigateToDashboard();
                }
            }
        });




        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                                .build();

        client = GoogleSignIn.getClient(requireActivity(), options);

        constraintLayoutGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(client.getSignInIntent(), 1234);
            }
        });

        btnLogin.setOnClickListener(view1 -> {
            loginUser();
        });


        txtCreateAccount.setOnClickListener(view2 -> {

            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = task.getResult();
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Log.i(" asd --> ", "onComplete: " + task.getResult().getUser().getDisplayName());
                                Log.i(" asd --> ", "onComplete: " + task.getResult().getUser().getEmail());

                                SharedUIMethods.saveUserInSharedPreference(requireContext(), Objects.requireNonNull(task.getResult().getUser().getDisplayName()), task.getResult().getUser().getEmail(), null, true);
                                navigateToDashboard();
                            } else {
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
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
                SharedUIMethods.saveUserInSharedPreference(requireContext(), null, email, password, true);
                navigateToDashboard();
            } else {
                Toast.makeText(getActivity(), Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToDashboard() {
        OnDashboardNavigationListener listener = (OnDashboardNavigationListener) getActivity();
        if (listener != null) {
            listener.navigateToDashboard();
        }
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