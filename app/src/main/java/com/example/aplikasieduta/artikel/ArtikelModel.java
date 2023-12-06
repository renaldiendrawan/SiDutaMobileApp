package com.example.aplikasieduta.artikel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtikelModel {

    public String id_artikel, judul_artikel, nama_kader, tanggal_artikel, isi_artikel;

    @Expose
    @SerializedName("img_artikel")
    public String img_path;

    public ArtikelModel(String id_artikel, String judul_artikel, String nama_kader, String tanggal_artikel, String img_path, String isi_artikel) {
        this.id_artikel = id_artikel;
        this.judul_artikel = judul_artikel;
        this.nama_kader = nama_kader;
        this.tanggal_artikel = tanggal_artikel;
        this.img_path = img_path;
        this.isi_artikel = isi_artikel;
    }

    public String getId_artikel() {
        return id_artikel;
    }

    public void setId_artikel(String id_artikel) {
        this.id_artikel = id_artikel;
    }

    public String getJudul_artikel() {
        return judul_artikel;
    }

    public void setJudul_artikel(String judul_artikel) {
        this.judul_artikel = judul_artikel;
    }

    public String getNama_kader() {
        return nama_kader;
    }

    public void setNama_kader(String nama_kader) {
        this.nama_kader = nama_kader;
    }

    public String getTanggal_artikel() {
        return tanggal_artikel;
    }

    public void setTanggal_artikel(String tanggal_artikel) {
        this.tanggal_artikel = tanggal_artikel;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getIsi_artikel() {
        return isi_artikel;
    }

    public void setIsi_artikel(String isi_artikel) {
        this.isi_artikel = isi_artikel;
    }
}
