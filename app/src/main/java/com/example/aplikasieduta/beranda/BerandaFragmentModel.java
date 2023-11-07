package com.example.aplikasieduta.beranda;

public class BerandaFragmentModel {

    private String nama_anak;
    private String tgl_lahir;
    private String jenis_kelamin;

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public BerandaFragmentModel(String nama_anak, String tgl_lahir, String jenis_kelamin, String usia) {
        this.nama_anak = nama_anak;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.usia = usia;
    }

    private String usia;



}
