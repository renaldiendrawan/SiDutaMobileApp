package com.example.aplikasieduta.beranda;

public class ViewPagerItem {

    int imageID;
    String judulartikel;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getJudulartikel() {
        return judulartikel;
    }

    public void setJudulartikel(String judulartikel) {
        this.judulartikel = judulartikel;
    }

    public ViewPagerItem(int imageID, String judulartikel) {
        this.imageID = imageID;
        this.judulartikel = judulartikel;

    }
}
