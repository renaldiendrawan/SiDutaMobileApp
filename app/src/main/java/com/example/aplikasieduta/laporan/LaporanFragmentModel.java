package com.example.aplikasieduta.laporan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanFragmentModel {

    @Expose
    @SerializedName("nama_anak")
    private String nama_anak;
    @Expose
    @SerializedName("tanggal_imunisasi")
    private String tanggal_imunisasi;
    @Expose
    @SerializedName("jenis_imunisasi")
    private String jenis_imunisasi;

    public LaporanFragmentModel(String nama_anak, String tanggal_imunisasi, String jenis_imunisasi) {
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
