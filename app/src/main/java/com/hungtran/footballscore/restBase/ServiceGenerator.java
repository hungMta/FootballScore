package com.hungtran.footballscore.restBase;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public class ServiceGenerator {

    public static final int TIMEOUT_SHORT = 9;
    public static final int TIMEOUT_LENGHT = 60*5;

    private static final String BASE_URL = "http://api.football-data.org";
    private static FootballApi footballApi;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static Retrofit retrofit;

    public static FootballApi resquest(Context mContex, int timeOut) {
        if (footballApi == null) {
            builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        httpClient = new OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.SECONDS).readTimeout(timeOut, TimeUnit.SECONDS).writeTimeout(timeOut, TimeUnit.SECONDS);
        httpClient.addInterceptor(httpLoggingInterceptor);

        retrofit = builder.client(httpClient.build()).build();
        footballApi = retrofit.create(FootballApi.class);
        return footballApi;
    }

}
