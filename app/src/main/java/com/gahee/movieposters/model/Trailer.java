package com.gahee.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable {

    @SerializedName("key")
    @Expose
    private String videoKey; //key

    @SerializedName("name")
    @Expose
    private String videoName; //name

    @SerializedName("site")
    @Expose
    private String videoSite; //site - YouTube

    @SerializedName("type")
    @Expose
    private String videoType; //type - Trailer

    public Trailer(){

    }

    public String getVideoKey() {
        return videoKey;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getVideoSite() {
        return videoSite;
    }

    public String getVideoType() {
        return videoType;
    }

    protected Trailer(Parcel in) {
        videoKey = in.readString();
        videoName = in.readString();
        videoSite = in.readString();
        videoType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoKey);
        dest.writeString(videoName);
        dest.writeString(videoSite);
        dest.writeString(videoType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
}
