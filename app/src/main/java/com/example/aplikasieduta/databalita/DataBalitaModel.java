package com.example.aplikasieduta.databalita;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBalitaModel {

    @Expose
    @SerializedName("id_anak")
    private String id_anak;
    @Expose
    @SerializedName("nama_anak")
    private String nama_anak;
    @Expose
    @SerializedName("tanggal_lahir_anak")
    private String tanggal_lahir_anak;
    @Expose
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @Expose
    @SerializedName("bb_lahir")
    private String bb_lahir;
    @Expose
    @SerializedName("tb_lahir")
    private String tb_lahir;
    @Expose
    @SerializedName("nama_ayah")
    private String nama_ayah;
    @Expose
    @SerializedName("nama_ibu")
    private String nama_ibu;
    @Expose
    @SerializedName("imagepath")
    private String imagepath;

    public DataBalitaModel(String nama_anak, String tanggal_lahir_anak, String jenis_kelamin, String bb_lahir, String tb_lahir, String nama_ayah, String nama_ibu, String imagepath) {
        this.nama_anak = nama_anak;
        this.tanggal_lahir_anak = tanggal_lahir_anak;
        this.jenis_kelamin = jenis_kelamin;
        this.bb_lahir = bb_lahir;
        this.tb_lahir = tb_lahir;
        this.nama_ayah = nama_ayah;
        this.nama_ibu = nama_ibu;
        this.imagepath = imagepath;
    }

    public String getId_anak() {
        return id_anak;
    }

    public void setId_anak(String id_anak) {
        this.id_anak = id_anak;
    }

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

    public String getBb_lahir() {
        return bb_lahir;
    }

    public void setBb_lahir(String bb_lahir) {
        this.bb_lahir = bb_lahir;
    }

    public String getTb_lahir() {
        return tb_lahir;
    }

    public void setTb_lahir(String tb_lahir) {
        this.tb_lahir = tb_lahir;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah = nama_ayah;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
