package com.example.aplikasieduta.retrofit;

import com.example.aplikasieduta.laporanfragments.LaporanImunisasiResponse;
import com.example.aplikasieduta.laporanfragments.LaporanPenimbanganResponse;
import com.example.aplikasieduta.profilakun.ProfilAkunResponse;
import com.example.aplikasieduta.beranda.BerandaFragmentResponse;
import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiResponse;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganResponse;
import com.example.aplikasieduta.model.LupaKataSandi.Lupa_katasandi;
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
            @Field("email") String email,
            @Field("kata_sandi") String kata_sandi
    );

    @FormUrlEncoded
    @POST("lupakatasandi_otp.php")
    Call<Lupa_katasandi> lupakatasandi_otp(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("lupakatasandi_ganti.php")
    Call<Lupa_katasandi> lupaKatasandi_ganti(
            @Field("email") String email,
            @Field("kode_otp") String kode_otp,
            @Field("password") String kata_sandi
    );

    @GET("selectjadwalpenimbangan.php")
    Call<JadwalPenimbanganResponse> ambiljadwalpenimbangan();

    @GET("selectjadwalimunisasi.php")
    Call<JadwalImunisasiResponse> ambiljadwalimunisasi();

    @GET("selectlaporanpenimbangan.php")
    Call<LaporanPenimbanganResponse> ambillaporanpenimbangan();

    @FormUrlEncoded
    @POST("selectlaporanimunisasi.php")
    Call<LaporanImunisasiResponse> ambillaporanimunisasi(
//            @Field("nik_ibu") String nik_ibu
    );

    @GET("databalita.php")
    Call<BerandaFragmentResponse> ambildatabalita();





//    @FormUrlEncoded
//    @POST("profilakun.php")
//    Call<ProfilAkunResponse> ambildataprofilakun (
//            @Field("nama_ibu") String nama_ibu,
//            @Field("nik_ibu") String nik_ibu,
//            @Field("tanggal_lahir") String tanggal_lahir,
//            @Field("alamat") String alamat,
//            @Field("email") String email,
//            @Field("imagepath") String imagepath
//    );

    @FormUrlEncoded
    @POST("ambilakun.php")
    Call<ProfilAkunResponse> ambilAkun(
            @Field("nik_ibu") String nik_ibu
    );

    @FormUrlEncoded
    @POST("profilakun.php")
    Call<ProfilAkunResponse> editAkun(
            @Field("id_ibu") String id,
            @Field("nik_ibu") String nik,
            @Field("nama_ibu") String nama,
            @Field("tanggal_lahir") String tanggal,
            @Field("alamat") String alamat,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("fotoprofil.php")
    Call<ProfilAkunResponse> updatePhoto(
            @Field("nik_ibu") String nik,
            @Field("imagepath") String image
    );

}
