package com.phonglt.pe.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "https://653e7ab29e8bd3be29df5c14.mockapi.io/";
    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static  XService xService(){
        return getClient().create(XService.class);
    }
    public static  YService yService(){
        return getClient().create(YService.class);
    }
}
