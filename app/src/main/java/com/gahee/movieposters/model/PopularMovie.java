package com.gahee.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularMovie implements Parcelable {

    @SerializedName("id")
    @Expose
    private int movieId; //id - for trailers

    @SerializedName("title")
    @Expose
    private String title; //title

    @SerializedName("overview")
    @Expose
    private String overview; //overview

    @SerializedName("poster_path")
    @Expose
    private String posterPath; //poster_path

    @SerializedName("release_date")
    @Expose
    private String releaseDate; //release_date

    @SerializedName("vote_average")
    @Expose
    private float voteAverage; //vote_average 6.4 ~

    public PopularMovie(){

    }

    protected PopularMovie(Parcel in) {
        movieId = in.readInt();
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeFloat(voteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PopularMovie> CREATOR = new Creator<PopularMovie>() {
        @Override
        public PopularMovie createFromParcel(Parcel in) {
            return new PopularMovie(in);
        }

        @Override
        public PopularMovie[] newArray(int size) {
            return new PopularMovie[size];
        }
    };

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }
}
