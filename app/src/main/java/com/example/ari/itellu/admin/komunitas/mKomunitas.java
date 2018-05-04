package com.example.ari.itellu.admin.komunitas;

import java.io.Serializable;

/**
 * Created by Tavs on 04/05/2018.
 */

public class mKomunitas implements Serializable {

    public String key;
    public String image_url;
    public String namaKomunitas;
    public String deskripsi;

    public mKomunitas() {

    }


    public mKomunitas(String key, String image_url, String namaKomunitas, String deskripsi) {
        this.key = key;
        this.image_url = image_url;
        this.namaKomunitas = namaKomunitas;
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

    public String getNamaKomunitas() {
        return namaKomunitas;
    }

    public void setNamaKomunitas(String namaKomunitas) {
        this.namaKomunitas = namaKomunitas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


}