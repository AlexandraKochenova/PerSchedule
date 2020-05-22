package com.example.client.helpers;

import com.example.client.interfaces.ServerApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    //private final static String BASE_URL = "http://192.168.0.56:5309/Service1.svc/";
    //private final static String BASE_URL = "http://192.168.1.9:5309/Service1.svc/";
    private final static String BASE_URL = "http://192.168.43.185:5310/Service1.svc/";
    //private final static String BASE_URL = "http://169.254.42.168:5310/Service1.svc/";
    private Retrofit mRetrofit;

    private NetworkService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ServerApi getJSONApi() {
        return mRetrofit.create(ServerApi.class);
    }
}
