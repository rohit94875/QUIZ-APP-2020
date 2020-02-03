package com.coviam.quizapp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App4 {
    static Retrofit retrofit;

    public static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
