package com.example.aplikasieduta.jadwalfragments;

public class JadwalPenimbanganModel {
    private String tanggalpenimbangan;
    private String jampenimbangan;
    private String tempatpenimbangan;

    public JadwalPenimbanganModel(String tanggalPenimbangan, String jamPenimbangan, String tempatPenimbangan) {
        this.tanggalpenimbangan = tanggalPenimbangan;
        this.jampenimbangan = jamPenimbangan;
        this.tempatpenimbangan = tempatPenimbangan;
    }

    public String getTanggalpenimbangan() {
        return tanggalpenimbangan;
    }

    public String getJampenimbangan() {
        return jampenimbangan;
    }

    public String getTempatpenimbangan() {
        return tempatpenimbangan;
    }
}
