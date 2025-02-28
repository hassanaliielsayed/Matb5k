package com.example.yourmeal.util;

import static com.example.yourmeal.util.Constants.ALREADY_LOGGED_IN;
import static com.example.yourmeal.util.Constants.EMAIL;
import static com.example.yourmeal.util.Constants.PASSWORD;
import static com.example.yourmeal.util.Constants.USER_NAME;

import android.content.Context;

public class SharedUIMethods {

    public static void saveUserInSharedPreference(Context context, String userName, String email, String password, boolean alreadyLoggedIn) {

        SharedPref.getInstance(context).putValue(USER_NAME, userName);
        SharedPref.getInstance(context).putValue(EMAIL, email);
        SharedPref.getInstance(context).putValue(PASSWORD, password);
        SharedPref.getInstance(context).putValue(ALREADY_LOGGED_IN, alreadyLoggedIn);

    }
}
