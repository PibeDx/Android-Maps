package com.josecuentas.android_maps.data.rest;

import com.josecuentas.android_maps.data.rest.entity.DirectionResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {

    private static String PATH = "https://maps.googleapis.com/maps/api/";
    private static Retrofit retrofit;


    public static ApiInterface getInterface() {
        retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .client(getBasicClientInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public static  Retrofit getRetrofit(){
        return retrofit;
    }


    public interface ApiInterface {
        @GET("directions/json")
        Call<DirectionResponse> searchDirecction(
                @Query("origin")
                        String origin,
                @Query("destination")
                        String destination,
                @Query("key")
                        String key);
    }

    public static OkHttpClient getBasicClientInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(logging);
        return builder.build();
    }
}
