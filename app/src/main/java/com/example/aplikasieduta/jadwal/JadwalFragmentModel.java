package com.example.aplikasieduta.jadwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalFragmentModel {

    @Expose
    @SerializedName("nama_anak")
    private String nama_anak;
    @Expose
    @SerializedName("tanggal_posyandu")
    private String tanggal_posyandu;
    @Expose
    @SerializedName("jam_posyandu")
    private String jam_posyandu;
    @Expose
    @SerializedName("tempat_posyandu")
    private String tempat_posyandu;
    @Expose
    @SerializedName("jenis_imunisasi")
    private String jenis_imunisasi;

    public JadwalFragmentModel(String namaAnak, String tanggalPosyandu, String jamPosyandu, String tempatPosyandu, String jenisImunisasi) {
        nama_anak = namaAnak;
        tanggal_posyandu = tanggalPosyandu;
        jam_posyandu = jamPosyandu;
        tempat_posyandu = tempatPosyandu;
        jenis_imunisasi = jenisImunisasi;

    }

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getTanggal_posyandu() {
        return tanggal_posyandu;
    }

    public void setTanggal_posyandu(String tanggal_posyandu) {
        this.tanggal_posyandu = tanggal_posyandu;
    }

    public String getJam_posyandu() {
        return jam_posyandu;
    }

    public void setJam_posyandu(String jam_posyandu) {
        this.jam_posyandu = jam_posyandu;
    }

    public String getTempat_posyandu() {
        return tempat_posyandu;
    }

    public void setTempat_posyandu(String tempat_posyandu) {
        this.tempat_posyandu = tempat_posyandu;
    }

    public String getJenis_imunisasi() {
        return jenis_imunisasi;
    }

    public void setJenis_imunisasi(String jenis_imunisasi) {
        this.jenis_imunisasi = jenis_imunisasi;
    }
}
