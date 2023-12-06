package com.example.aplikasieduta.jadwalfragments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JadwalPenimbanganResponse {
    private boolean success;
    String message;
    @SerializedName("data")
    ArrayList<JadwalPenimbanganModel> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<JadwalPenimbanganModel> getData() {
        return data;
    }

    public void setData(ArrayList<JadwalPenimbanganModel> data) {
        this.data = data;
    }

}