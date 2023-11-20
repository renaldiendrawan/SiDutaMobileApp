package com.example.aplikasieduta.beranda;

public class BerandaFragmentModel {

    private String nama_anak;
    private String tanggal_lahir_anak;
    private String jenis_kelamin;

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTanggal_lahir_anak() {
        return tanggal_lahir_anak;
    }

    public void setTanggal_lahir_anak(String tanggal_lahir_anak) {
        this.tanggal_lahir_anak = tanggal_lahir_anak;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

//    public String getUsia() {
//        return usia;
//    }
//
//    public void setUsia(String usia) {
//        this.usia = usia;
//    }

    public BerandaFragmentModel(String nama_anak, String tanggal_lahir_anak, String jenis_kelamin, String usia) {
        this.nama_anak = nama_anak;
        this.tanggal_lahir_anak = tanggal_lahir_anak;
        this.jenis_kelamin = jenis_kelamin;
//        this.usia = usia;
    }

//    private String usia;
}
