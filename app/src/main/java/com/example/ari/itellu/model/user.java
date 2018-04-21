package com.example.ari.itellu.model;

/**
 * Created by Ari on 24/03/2018.
 */

public class user {
    private String nama;
    private String password;
    private String email;

    public user() {
    }

    public user(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
