package com.gahee.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Trailer> trailers;

    public TrailerResponse(){

    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    protected TrailerResponse(Parcel in) {
        trailers = in.createTypedArrayList(Trailer.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(trailers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerResponse> CREATOR = new Creator<TrailerResponse>() {
        @Override
        public TrailerResponse createFromParcel(Parcel in) {
            return new TrailerResponse(in);
        }

        @Override
        public TrailerResponse[] newArray(int size) {
            return new TrailerResponse[size];
        }
    };
}
