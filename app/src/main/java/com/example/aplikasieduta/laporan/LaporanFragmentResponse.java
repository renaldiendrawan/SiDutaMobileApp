package com.example.aplikasieduta.laporan;

import com.example.aplikasieduta.jadwal.JadwalFragmentModel;

import java.util.ArrayList;

public class LaporanFragmentResponse {

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LaporanFragmentResponse(String status, ArrayList<LaporanFragmentModel> data) {
        this.status = status;
        this.data = data;
    }

    public ArrayList<LaporanFragmentModel> getData() {
        return data;
    }

    public void setData(ArrayList<LaporanFragmentModel> data) {
        this.data = data;
    }

    public ArrayList<LaporanFragmentModel> data;

}

