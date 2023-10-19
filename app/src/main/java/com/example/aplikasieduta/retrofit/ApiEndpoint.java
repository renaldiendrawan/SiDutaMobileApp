package com.example.aplikasieduta.retrofit;

import com.example.aplikasieduta.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("data.php")
    Call<UserModel> getUser();
}
