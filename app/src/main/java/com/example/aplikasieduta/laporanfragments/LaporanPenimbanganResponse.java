package com.example.aplikasieduta.laporanfragments;

import java.util.ArrayList;

public class LaporanPenimbanganResponse {
    private boolean success;
    String message;
    ArrayList<LaporanPenimbanganModel> data;

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

    public ArrayList<LaporanPenimbanganModel> getData() {
        return data;
    }

    public void setData(ArrayList<LaporanPenimbanganModel> data) {
        this.data = data;
    }

}
