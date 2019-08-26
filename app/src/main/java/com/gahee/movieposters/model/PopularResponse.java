package com.gahee.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularResponse implements Parcelable {

    @SerializedName("results")
    @Expose
    List<PopularMovie> results; //results

    PopularResponse(){

    }

    public List<PopularMovie> getResults() {
        return results;
    }

    protected PopularResponse(Parcel in) {
        results = in.createTypedArrayList(PopularMovie.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PopularResponse> CREATOR = new Creator<PopularResponse>() {
        @Override
        public PopularResponse createFromParcel(Parcel in) {
            return new PopularResponse(in);
        }

        @Override
        public PopularResponse[] newArray(int size) {
            return new PopularResponse[size];
        }
    };
}
