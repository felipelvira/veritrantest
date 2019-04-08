package com.veritran.felipeelvira.veritran_test.Services;

import com.veritran.felipeelvira.veritran_test.Models.VeritranUser;
import com.veritran.felipeelvira.veritran_test.Utils.AuthBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @POST("users/login")
    Call<VeritranUser> login(@Body AuthBody authBody);

    @POST("users/register")
    Call<VeritranUser> register(@Body AuthBody authBody);

    @GET("users/all")
    Call<List<VeritranUser>>all(@Query("userId") String userId);

}
