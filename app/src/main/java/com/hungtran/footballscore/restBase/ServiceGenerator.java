package com.hungtran.footballscore.restBase;


import android.content.Context;

import com.hungtran.footballscore.utils.Logg;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public class ServiceGenerator {

    public static final int TIMEOUT_SHORT = 9;
    public static final int TIMEOUT_LENGHT = 60 * 5;
    public static final String AU_TOKEN = "5200cfe1df6048f5bf0f508cf1902774";

    private static final String BASE_URL = "http://api.football-data.org";
    private static final String BASE_UPLOAD_LOGO = "https://upload.wikimedia.org";
    private static FootballApi footballApi;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static Retrofit retrofit;

    public static FootballApi resquest(Context mContex, int timeOut) {
        if (footballApi == null) {
            builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addConverterFactory(BitmapConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        httpClient = new OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.SECONDS).readTimeout(timeOut, TimeUnit.SECONDS).writeTimeout(timeOut, TimeUnit.SECONDS);
        httpClient.addInterceptor(httpLoggingInterceptor);
        AuthenticationInterceptor interceptor =
                new AuthenticationInterceptor(AU_TOKEN);
        httpClient.addInterceptor(interceptor);
        retrofit = builder.client(httpClient.build()).build();
        footballApi = retrofit.create(FootballApi.class);
        return footballApi;
    }

//    public static FootballApi downloadImage(int timeOut){
//
//        if (footballApi == null) {
//            builder = new Retrofit.Builder().baseUrl(BASE_UPLOAD_LOGO).addConverterFactory(GsonConverterFactory.create());
//            httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
//        httpClient = new OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.SECONDS).readTimeout(timeOut, TimeUnit.SECONDS).writeTimeout(timeOut, TimeUnit.SECONDS);
//        httpClient.addInterceptor(httpLoggingInterceptor);
//        retrofit = builder.client(httpClient.build()).build();
//        footballApi = retrofit.create(FootballApi.class);
//        return retrofit.create(FootballApi.class);
//    }


    private static class AuthenticationInterceptor implements Interceptor {
        public String authToken;

        public AuthenticationInterceptor(String token) {
            this.authToken = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder builder = original.newBuilder()
                    .header("X-Auth-Token", authToken);

            Request request = builder.build();
            return chain.proceed(request);
        }
    }
}
