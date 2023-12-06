package com.example.aplikasieduta.jadwalfragments;

public class JadwalImunisasiModel {
    private String tanggal_posyandu;
    private String jam_posyandu;
    private String tempat_posyandu;
    private String jenis_imunisasi;

    public JadwalImunisasiModel(String tanggal_posyandu, String jam_posyandu, String tempat_posyandu, String jenis_imunisasi) {
        this.tanggal_posyandu = tanggal_posyandu;
        this.jam_posyandu = jam_posyandu;
        this.tempat_posyandu = tempat_posyandu;
        this.jenis_imunisasi = jenis_imunisasi;
    }

    public String getTgl_imunisasi() {
        return tanggal_posyandu;
    }

    public void setTgl_imunisasi(String tanggal_posyandu) {
        this.tanggal_posyandu = tanggal_posyandu;
    }

    public String getJam_imunisasi() {
        return jam_posyandu;
    }

    public void setJam_imunisasi(String jam_posyandu) {
        this.jam_posyandu = jam_posyandu;
    }

    public String getTempat_imunisasi() {
        return tempat_posyandu;
    }

    public void setTempat_imunisasi(String tempat_posyandu) {
        this.tempat_posyandu = tempat_posyandu;
    }

    public String getJenis_imunisasi() {
        return jenis_imunisasi;
    }

    public void setJenis_imunisasi(String jenis_imunisasi) {
        this.jenis_imunisasi = jenis_imunisasi;
    }
}