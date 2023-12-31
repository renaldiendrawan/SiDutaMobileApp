package com.example.aplikasieduta.laporanfragments;

public class LaporanPenimbanganModel {
    private String nama_anak, umur, tgl_penimbangan, berat_badan, tinggi_badan;

    public LaporanPenimbanganModel(String nama_anak, String umur, String tanggal_penimbangan, String berat_badan, String tinggi_badan) {
        this.nama_anak = nama_anak;
        this.tgl_penimbangan = tanggal_penimbangan;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
    }

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTgl_penimbangan() {
        return tgl_penimbangan;
    }

    public void setTgl_penimbangan(String tgl_penimbangan) {
        this.tgl_penimbangan = tgl_penimbangan;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(String tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

}
