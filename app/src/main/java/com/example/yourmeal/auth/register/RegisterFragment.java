package com.example.yourmeal.auth.register;

import static com.example.yourmeal.util.Constants.ALREADY_LOGGED_IN;
import static com.example.yourmeal.util.Constants.EMAIL;
import static com.example.yourmeal.util.Constants.PASSWORD;
import static com.example.yourmeal.util.Constants.USER_NAME;
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
import com.example.yourmeal.auth.OnDashboardNavigationListener;
import com.example.yourmeal.util.SharedPref;
import com.example.yourmeal.util.SharedUIMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;


public class RegisterFragment extends Fragment {

    TextInputEditText txtInputUserName, txtInputEmail, txtInputPassword, txtInputConfirmPassword;
    MaterialButton btnRegister;
    ProgressBar progressBar;
    TextView txtLogin, txtGuest;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtInputUserName = view.findViewById(R.id.txtInputUserNameET);
        txtInputEmail = view.findViewById(R.id.txtInputEmailET);
        txtInputPassword = view.findViewById(R.id.txtInputPassET);
        txtInputConfirmPassword = view.findViewById(R.id.txtInputConfirmedPassET);
        progressBar = view.findViewById(R.id.progress_bar);
        btnRegister = view.findViewById(R.id.btnRegister);
        txtLogin = view.findViewById(R.id.txtLogin);
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


        btnRegister.setOnClickListener(view1 -> createAccount());
        txtLogin.setOnClickListener(view2 -> {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
        });

    }

    private void createAccount() {
        String userName = Objects.requireNonNull(txtInputUserName.getText()).toString();
        String email = Objects.requireNonNull(txtInputEmail.getText()).toString();
        String password = Objects.requireNonNull(txtInputPassword.getText()).toString();
        String confirmPassword = Objects.requireNonNull(txtInputConfirmPassword.getText()).toString();

        boolean isValidateData = validateData(userName, email, password, confirmPassword);
        if (isValidateData){
            createAccountOnFirebase(email, password);
        }
    }

    private void createAccountOnFirebase(String email, String password) {
        changeProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeProgress(false);
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "Account created successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                    if (currentUser != null){
                        SharedUIMethods.saveUserInSharedPreference(requireContext(), Objects.requireNonNull(txtInputUserName.getText()).toString(), email, password, true);
                        navigateToHomeScreen();
                    }
                } else {
                    Toast.makeText(getActivity(), Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToHomeScreen() {
        OnDashboardNavigationListener listener = (OnDashboardNavigationListener) getActivity();
        if (listener != null) {
            listener.navigateToDashboard();
        }
    }



    private void changeProgress(boolean inProgress){
        if (inProgress){
           progressBar.setVisibility(View.VISIBLE);
           btnRegister.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateData(String userName, String email, String password, String confirmPassword){
        boolean result = true;
        if (userName.isEmpty() || userName.length() < 4 || !userName.matches("[a-zA-Z0-9]+")){
            txtInputUserName.setError("Username is Invalid");
            result = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtInputEmail.setError("Email is Invalid");
            result = false;
        }
        if (password.length() < 8){
            txtInputPassword.setError("Password is Invalid");
            result = false;
        }
        if (!confirmPassword.equals(password)){
            txtInputConfirmPassword.setError("password is not matched");
            result = false;
        }

        return result;

    }
}