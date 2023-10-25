package com.example.aplikasieduta.retrofit;

import com.example.aplikasieduta.UserModel;
import com.example.aplikasieduta.model.login.Login;
import com.example.aplikasieduta.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
          @Field("nik_ibu") String nik_ibu,
          @Field("kata_sandi") String kata_sandi
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("nama_ibu") String nama_ibu,
            @Field("nik_ibu") String nik_ibu,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("alamat") String alamat,
            @Field("nomor_telepon") String nomor_telepon,
            @Field("kata_sandi") String kata_sandi
    );

}
