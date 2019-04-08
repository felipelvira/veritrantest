package com.veritran.felipeelvira.veritran_test.Services;

import android.content.Context;
import android.util.Log;

import com.veritran.felipeelvira.veritran_test.LoginActivity;
import com.veritran.felipeelvira.veritran_test.Utils.Preferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://sheltered-forest-98200.herokuapp.com/";
    //private static final String BASE_URL = "http://10.0.2.2:5000/";


    public static Retrofit getClientInstance(final Context context) {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " +  Preferences.get(context).getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        if (retrofit == null) {
            Log.v("base url", BASE_URL);
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
