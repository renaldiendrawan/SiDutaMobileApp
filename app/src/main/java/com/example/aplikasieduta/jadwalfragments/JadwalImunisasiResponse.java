package com.example.aplikasieduta.jadwalfragments;

import java.util.ArrayList;

public class JadwalImunisasiResponse {
    private boolean success;
    String message;
    ArrayList<JadwalImunisasiModel> data;

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

    public ArrayList<JadwalImunisasiModel> getData() {
        return data;
    }

    public void setData(ArrayList<JadwalImunisasiModel> data) {
        this.data = data;
    }

}