package com.example.aplikasieduta.profilakun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProfilAkunResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("data")
    ArrayList<ProfilAkunModel> data;

    @Expose
    @SerializedName("imagepath")
    private String imagepath;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ProfilAkunModel> getData() {
        return data;
    }

    public void setData(ArrayList<ProfilAkunModel> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
