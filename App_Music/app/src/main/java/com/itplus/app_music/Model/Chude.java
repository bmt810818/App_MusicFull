package com.itplus.app_music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chude implements Serializable {

@SerializedName("idtheloai")
@Expose
private String idtheloai;
@SerializedName("idchude")
@Expose
private String idchude;
@SerializedName("tenchude")
@Expose
private String tenchude;
@SerializedName("hinhchude")
@Expose
private String hinhchude;

public String getIdtheloai() {
return idtheloai;
}

public void setIdtheloai(String idtheloai) {
this.idtheloai = idtheloai;
}

public String getIdchude() {
return idchude;
}

public void setIdchude(String idchude) {
this.idchude = idchude;
}

public String getTenchude() {
return tenchude;
}

public void setTenchude(String tenchude) {
this.tenchude = tenchude;
}

public String getHinhchude() {
return hinhchude;
}

public void setHinhchude(String hinhchude) {
this.hinhchude = hinhchude;
}

}