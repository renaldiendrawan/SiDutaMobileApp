package com.example.aplikasieduta.jadwalfragments;

public class JadwalPenimbanganModel {
    private String tanggal_posyandu, jam_posyandu, tempat_posyandu;

    public JadwalPenimbanganModel(String tanggal_posyandu, String jam_posyandu, String tempat_posyandu) {
        this.tanggal_posyandu = tanggal_posyandu;
        this.jam_posyandu = jam_posyandu;
        this.tempat_posyandu = tempat_posyandu;
    }

    public String getTgl_penimbangan() {
        return tanggal_posyandu;
    }

    public void setTgl_penimbangan(String tanggal_posyandu) {
        this.tanggal_posyandu = tanggal_posyandu;
    }

    public String getJam_penimbangan() {
        return jam_posyandu;
    }

    public void setJam_penimbangan(String jam_posyandu) {
        this.jam_posyandu = jam_posyandu;
    }

    public String getTempat_penimbangan() {
        return tempat_posyandu;
    }

    public void setTempat_penimbangan(String tempat_posyandu) {
        this.tempat_posyandu = tempat_posyandu;
    }
}
