package com.itplus.app_music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Theloai implements Serializable {

    @SerializedName("idtheloai")
    @Expose
    private String idtheloai;
    @SerializedName("tentheloai")
    @Expose
    private String tentheloai;
    @SerializedName("hinhtheloai")
    @Expose
    private String hinhtheloai;

    public String getIdtheloai() {
        return idtheloai;
    }

    public void setIdtheloai(String idtheloai) {
        this.idtheloai = idtheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getHinhtheloai() {
        return hinhtheloai;
    }

    public void setHinhtheloai(String hinhtheloai) {
        this.hinhtheloai = hinhtheloai;
    }

}
