package com.example.aplikasieduta.jadwalfragments;

public class JadwalImunisasiModel {
    private String tanggalimunisasi;
    private String jamimunisasi;
    private String tempatimunisasi;
    private String jenisvaksin;

    public JadwalImunisasiModel(String tanggalImunisasi, String jamImunisasi, String tempatImunisasi, String jenisVaksin) {
        this.tanggalimunisasi = tanggalImunisasi;
        this.jamimunisasi = jamImunisasi;
        this.tempatimunisasi = tempatImunisasi;
        this.jenisvaksin = jenisVaksin;
    }

    public String getTanggalimunisasi() {
        return tanggalimunisasi;
    }

    public String getJamimunisasi() {
        return jamimunisasi;
    }

    public String getTempatimunisasi() {
        return tempatimunisasi;
    }

    public String getJenisvaksin() {
        return jenisvaksin;
    }
}
