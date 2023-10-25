package com.example.aplikasieduta;

public class BerandaFragmentModel {

    private String namabalita;
    private String umurbalita;
    private String jeniskelaminbalita;

    public BerandaFragmentModel(String namaBalita, String umurBalita, String jeniskelaminBalita) {
        this.namabalita = namaBalita;
        this.umurbalita = umurBalita;
        this.jeniskelaminbalita = jeniskelaminBalita;
    }

    public String getNamabalita() {
        return namabalita;
    }

    public String getUmurbalita() {
        return umurbalita;
    }

    public String getJeniskelaminbalita() {
        return jeniskelaminbalita;
    }
}
