package com.example.aplikasieduta.retrofit;

import com.example.aplikasieduta.artikel.ArtikelResponse;
import com.example.aplikasieduta.databalita.DataBalitaResponse;
import com.example.aplikasieduta.databalita.DataBalitaUpdateRespon;
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
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
            @Field("kata_sandi") String kata_sandi
    );

    @GET("selectjadwalpenimbangan.php")
    Call<JadwalPenimbanganResponse> ambiljadwalpenimbangan();

    @GET("selectjadwalimunisasi.php")
    Call<JadwalImunisasiResponse> ambiljadwalimunisasi();

    @GET("selectlaporanpenimbangan.php")
    Call<LaporanPenimbanganResponse> ambillaporanpenimbangan(
            @Query("id_ibu") String nama_anak
    );

    @GET("selectlaporanimunisasi.php")
    Call<LaporanImunisasiResponse> ambillaporanimunisasi(
            @Query("id_ibu") String nama_anak
    );

    @GET("databalita.php")
    Call<DataBalitaResponse> ambildatabalita(
            @Field("nama_anak") String namaAnak,
            @Field("tanggal_lahir_anak") String tanggalLahir,
            @Field("jenis_kelamin") String jenisKel
    );

    @GET("listbalita.php")
    Call<DataBalitaResponse> listData(
            @Query("id_ibu") String  idIbu
    );

    @GET("databalita.php")
    Call<DataBalitaResponse> getDataBalita(
            @Query("nama_anak") String nama
    );

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

    @FormUrlEncoded
    @POST("fotobalita.php")
    Call<DataBalitaResponse> updatePhotoBalita(
            @Field("nama_anak") String namaAnak,
            @Field("imagepath") String image
    );

    @FormUrlEncoded
    @POST("databalita.php")
    Call<DataBalitaUpdateRespon> editDataBalita(
            @Field("id_anak") String idAnak,
            @Field("nama_anak") String nama_anak,
            @Field("tanggal_lahir_anak") String tanggal_lahir_anak,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("bb_lahir") String bb_lahir,
            @Field("tb_lahir") String tb_lahir,
            @Field("nama_ayah") String nama_ayah,
            @Field("nama_ibu") String nama_ibu
    );

    @GET("get_fotobalita.php")
    Call<DataBalitaResponse> getPhoto(
      @Query("nama_anak") String namaAnak
    );

    @GET("artikel.php")
    Call<ArtikelResponse> artikel();

    @GET("detail_artikel.php")
    Call<ArtikelResponse> detailartikel(
            @Query("judul_artikel") String judulArtikel
    );
}
