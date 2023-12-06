package com.example.aplikasieduta.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id_ibu")
    private String idIbu;

    @SerializedName("nama_ibu")
    private String namaIbu;

    @SerializedName("nik_ibu")
    private String nikIbu;

    @Expose
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @Expose
    @SerializedName("alamat")
    private String alamat;
    @Expose
    @SerializedName("email")
    private String email;

    public LoginData() {
    }

    public String getNikIbu() {
        return nikIbu;
    }

    public void setNikIbu(String nikIbu) {
        this.nikIbu = nikIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public String getNamaIbu() {
        return namaIbu;
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

}
