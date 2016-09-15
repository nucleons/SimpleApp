package com.example.aditya.simpleapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aditya on 13/9/16.
 */
public class RetrofitBuilder {

    private static final String BASE_URL = "https://gist.githubusercontent.com";

    private static RetrofitBuilder networkInstance;
    private Retrofit retrofit;

    public static RetrofitBuilder getInstance() {
        if (networkInstance == null)
            networkInstance = new RetrofitBuilder();
        return networkInstance;
    }

    private RetrofitBuilder(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
