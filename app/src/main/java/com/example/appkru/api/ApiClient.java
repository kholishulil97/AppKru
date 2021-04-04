package com.example.appkru.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haerul on 15/03/18.
 */

public class ApiClient {

    public static final String BASE_URL = "http://192.168.1.17/";

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

}
