package com.example.yourmeal.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String SHEARED_PREFERENCE = "SHEARED_PREFERENCE";
    private static volatile SharedPref instance = null;

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    private SharedPref(Context context){
        preferences = context.getSharedPreferences(SHEARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPref getInstance(Context context){
        if (instance == null){
            synchronized (SharedPref.class){
                if (instance == null){
                    instance = new SharedPref(context);
                }
            }
        }
        return instance;
    }

    public void putValue(String key, Object value){
        if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String){
            editor.putString(key, (String) value);
        }

        editor.apply();
    }

    public boolean getBooleanValue(String key, boolean defaultValue){
        return preferences.getBoolean(key, defaultValue);
    }

    public String getStringValue(String key, String defaultValue){
        return preferences.getString(key, defaultValue);
    }


}
