package com.itplus.app_music.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihathot implements Parcelable {

@SerializedName("idbaihat")
@Expose
private String idbaihat;
@SerializedName("tenbaihat")
@Expose
private String tenbaihat;
@SerializedName("hinhbaihat")
@Expose
private String hinhbaihat;
@SerializedName("casi")
@Expose
private String casi;
@SerializedName("linkbaihat")
@Expose
private String linkbaihat;
@SerializedName("like")
@Expose
private String like;

    protected Baihathot(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        like = in.readString();
    }

    public static final Creator<Baihathot> CREATOR = new Creator<Baihathot>() {
        @Override
        public Baihathot createFromParcel(Parcel in) {
            return new Baihathot(in);
        }

        @Override
        public Baihathot[] newArray(int size) {
            return new Baihathot[size];
        }
    };

    public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkbaihat() {
return linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.linkbaihat = linkbaihat;
}

public String getLike() {
return like;
}

public void setLike(String like) {
this.like = like;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idbaihat);
        dest.writeString(tenbaihat);
        dest.writeString(hinhbaihat);
        dest.writeString(casi);
        dest.writeString(linkbaihat);
        dest.writeString(like);
    }
}