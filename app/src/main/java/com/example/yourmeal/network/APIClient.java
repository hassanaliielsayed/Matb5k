package com.example.yourmeal.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "https://themealdb.com/";

    private final APIService service;

    private volatile static APIClient instanse = null;

    private APIClient(){
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(APIService.class);
    }

    public static APIClient getInstance(){
        if (instanse == null){
            synchronized (APIClient.class){
                if (instanse == null){
                    instanse = new APIClient();
                }
            }
        }
        return instanse;
    }

    public APIService getService(){
        return service;
    }


}
