package com.example.aplikasieduta.profilakun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilAkunModel {

    @Expose
    @SerializedName("id_ibu")
    private String idIbu;
    @Expose
    @SerializedName("nama_ibu")
    private String nama_ibu;
    @Expose
    @SerializedName("nik_ibu")
    private String nik_ibu;
    @Expose
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @Expose
    @SerializedName("alamat")
    private String alamat;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("imagepath")
    private String imagepath;

    public ProfilAkunModel(String nama_ibu, String nik_ibu, String tanggal_lahir, String alamat, String email) {
        this.nama_ibu = nama_ibu;
        this.nik_ibu = nik_ibu;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.email = email;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getNik_ibu() {
        return nik_ibu;
    }

    public void setNik_ibu(String nik_ibu) {
        this.nik_ibu = nik_ibu;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdIbu() {
        return idIbu;
    }

    public void setIdIbu(String idIbu) {
        this.idIbu = idIbu;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
