package com.example.yourmeal.local;

import androidx.room.TypeConverter;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ObjectConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromIngredientList(Object object) {
        if (object == null) {
            return null;
        }
        // Convert the list to JSON
        return gson.toJson(object);
    }

    @TypeConverter
    public static Object toObject(String json) {
        if (json == null) {
            return null;
        }
        // Convert JSON back to Object
        Type type = new TypeToken<Object>() {}.getType();
        return gson.fromJson(json, type);
    }
}
