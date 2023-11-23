package com.example.aplikasieduta.laporanfragments;

public class LaporanImunisasiModel {
    private String nama_anak;
    private String umur;
    private String tanggal_imunisasi;
    private String jenis_imunisasi;

    public LaporanImunisasiModel(String nama_anak, String umur, String tanggal_imunisasi, String jenis_imunisasi) {
        this.nama_anak = nama_anak;
        this.tanggal_imunisasi = tanggal_imunisasi;
        this.jenis_imunisasi = jenis_imunisasi;
    }

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTanggal_imunisasi() {
        return tanggal_imunisasi;
    }

    public void setTanggal_imunisasi(String tanggal_imunisasi) {
        this.tanggal_imunisasi = tanggal_imunisasi;
    }

    public String getJenis_imunisasi() {
        return jenis_imunisasi;
    }

    public void setJenis_imunisasi(String jenis_imunisasi) {
        this.jenis_imunisasi = jenis_imunisasi;
    }
}
