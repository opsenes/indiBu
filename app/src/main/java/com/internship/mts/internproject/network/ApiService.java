package com.internship.mts.internproject.network;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    // Sürekli değişiyor backend'e sorulup değiştirilecek her gün.
    private static final String BASE_URL_HTTP = "http://10.58.4.146:8080";
    private static ApiService instance;

    @NonNull
    private final Retrofit retrofitClient;

    private ApiService(@NonNull String baseUrl) {

        OkHttpClient client;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor());
        builder.addInterceptor(new ReceivedCookiesInterceptor());
        client = builder.build();

        retrofitClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        if (instance == null) {
            synchronized (ApiService.class) {
                if (instance == null) {
                    instance = new ApiService(BASE_URL_HTTP);
                }
            }
        }
        return instance;
    }

    public <T> T getApi(Class<T> apiClass) {
        return retrofitClient.create(apiClass);
    }

    public static String getBaseUrlHttp() {
        return BASE_URL_HTTP;
    }
}
