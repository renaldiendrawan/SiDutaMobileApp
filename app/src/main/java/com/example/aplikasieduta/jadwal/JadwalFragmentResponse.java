package com.example.aplikasieduta.jadwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JadwalFragmentResponse {

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JadwalFragmentResponse(String status, ArrayList<JadwalFragmentModel> data) {
        this.status = status;
        this.data = data;
    }

    public ArrayList<JadwalFragmentModel> getData() {
        return data;
    }

    public void setData(ArrayList<JadwalFragmentModel> data) {
        this.data = data;
    }

    public ArrayList<JadwalFragmentModel> data;

}
