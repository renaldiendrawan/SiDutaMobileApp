package com.example.aplikasieduta.beranda;

import java.util.ArrayList;

public class BerandaFragmentResponse {
    private boolean success;
    String message;
    ArrayList<BerandaFragmentModel> data;

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

    public ArrayList<BerandaFragmentModel> getData() {
        return data;
    }

    public void setData(ArrayList<BerandaFragmentModel> data) {
        this.data = data;
    }
}
