package com.example.ari.itellu.admin.Event;

import java.io.Serializable;

/**
 * Created by Tavs on 05/05/2018.
 */

public class mEvent implements Serializable {
    public String key;
    public String image_url;
    public String namaEvent;
    public String lokasiEvent;
    public String deskripsiEvent;
    public String tglEvent;

    public mEvent(String key, String image_url, String namaEvent, String lokasiEvent,
                  String deskripsiEvent, String tglEvent) {
        this.key = key;
        this.image_url = image_url;
        this.namaEvent = namaEvent;
        this.lokasiEvent = lokasiEvent;
        this.deskripsiEvent = deskripsiEvent;
        this.tglEvent = tglEvent;
    }

    public mEvent() {

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

    public String getNamaEvent() {
        return namaEvent;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public String getLokasiEvent() {
        return lokasiEvent;
    }

    public void setLokasiEvent(String lokasiEvent) {
        this.lokasiEvent = lokasiEvent;
    }

    public String getDeskripsiEvent() {
        return deskripsiEvent;
    }

    public void setDeskripsiEvent(String deskripsiEvent) {
        this.deskripsiEvent = deskripsiEvent;
    }

    public String getTglEvent() {
        return tglEvent;
    }

    public void setTglEvent(String tglEvent) {
        this.tglEvent = tglEvent;
    }


}
