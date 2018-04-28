package com.example.ari.itellu.admin.ukm;

import java.io.Serializable;

/**
 * Created by Tavs on 23/04/2018.
 */

public class mUkm implements Serializable {

    public String key;
    public String image_url;
    public String namaUkm;
    public String deskripsi;

    public mUkm(){

    }


    public mUkm(String key, String image_url, String namaUkm, String deskripsi) {
        this.key = key;
        this.image_url = image_url;
        this.namaUkm = namaUkm;
        this.deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getNamaUkm() {
        return namaUkm;
    }

    public void setNamaUkm(String namaUkm) {
        this.namaUkm = namaUkm;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }





}
