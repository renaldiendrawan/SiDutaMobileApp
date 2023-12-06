package com.example.aplikasieduta.databalita;

import com.example.aplikasieduta.profilakun.ProfilAkunModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataBalitaResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("data")
    ArrayList<DataBalitaModel> data;

    @Expose
    @SerializedName("imagepath")
    private String imagepath;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DataBalitaModel> getData() {
        return data;
    }

    public void setData(ArrayList<DataBalitaModel> data) {
        this.data = data;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
