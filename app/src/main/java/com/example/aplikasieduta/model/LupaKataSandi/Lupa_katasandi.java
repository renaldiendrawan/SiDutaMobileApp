package com.example.aplikasieduta.model.LupaKataSandi;

import com.google.gson.annotations.SerializedName;

public class Lupa_katasandi {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;
}
