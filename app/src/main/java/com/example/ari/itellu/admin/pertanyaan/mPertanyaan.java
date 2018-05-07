package com.example.ari.itellu.admin.pertanyaan;

import java.io.Serializable;

/**
 * Created by Tavs on 08/05/2018.
 */

public class mPertanyaan implements Serializable {
    public String key_pertanyaan;
    public String namaPertanyaan;
    public String judulPertanyaan;
    public String deskripsiPertanyaan;

    public mPertanyaan(String key_pertanyaan, String namaPertanyaan,
                       String judulPertanyaan, String deskripsiPertanyaan) {
        this.key_pertanyaan = key_pertanyaan;
        this.namaPertanyaan = namaPertanyaan;
        this.judulPertanyaan = judulPertanyaan;
        this.deskripsiPertanyaan = deskripsiPertanyaan;
    }

    public String getKey_pertanyaan() {
        return key_pertanyaan;
    }

    public void setKey_pertanyaan(String key_pertanyaan) {
        this.key_pertanyaan = key_pertanyaan;
    }

    public String getNamaPertanyaan() {
        return namaPertanyaan;
    }

    public void setNamaPertanyaan(String namaPertanyaan) {
        this.namaPertanyaan = namaPertanyaan;
    }

    public String getJudulPertanyaan() {
        return judulPertanyaan;
    }

    public void setJudulPertanyaan(String judulPertanyaan) {
        this.judulPertanyaan = judulPertanyaan;
    }

    public String getDeskripsiPertanyaan() {
        return deskripsiPertanyaan;
    }

    public void setDeskripsiPertanyaan(String deskripsiPertanyaan) {
        this.deskripsiPertanyaan = deskripsiPertanyaan;
    }

    public mPertanyaan() {

    }
}
