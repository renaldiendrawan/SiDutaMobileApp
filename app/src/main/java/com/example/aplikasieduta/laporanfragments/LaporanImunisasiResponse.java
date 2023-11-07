package com.example.aplikasieduta.laporanfragments;

import java.util.ArrayList;

public class LaporanImunisasiResponse {
    private boolean success;
    String message;
    ArrayList<LaporanImunisasiModel> data;

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

    public ArrayList<LaporanImunisasiModel> getData() {
        return data;
    }

    public void setData(ArrayList<LaporanImunisasiModel> data) {
        this.data = data;
    }
}
