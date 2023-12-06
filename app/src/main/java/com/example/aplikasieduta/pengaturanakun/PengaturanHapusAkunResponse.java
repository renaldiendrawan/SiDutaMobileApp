package com.example.aplikasieduta.pengaturanakun;

public class PengaturanHapusAkunResponse {

    private String status;
    private String message;
    private boolean success;

    public PengaturanHapusAkunResponse(String status, String message) {
        this.status = status;
        this.message = message;
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

    public boolean isSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

