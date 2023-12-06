package com.example.aplikasieduta.artikel;

import java.util.ArrayList;

public class ArtikelResponse {

    private String status;
    private String message;
    private ArrayList<ArtikelModel> data;

    public ArtikelResponse(String status, String message, ArrayList<ArtikelModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

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

    public ArrayList<ArtikelModel> getData() {
        return data;
    }

    public void setData(ArrayList<ArtikelModel> data) {
        this.data = data;
    }

}
