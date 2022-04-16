package com.example.hw51;

import android.app.Application;

import com.example.hw51.data.remote.FilmApi;
import com.example.hw51.data.remote.RetrofitClient;

import retrofit2.Retrofit;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmApi api;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient=new RetrofitClient();
        api=retrofitClient.createFilmApi();

    }
}
