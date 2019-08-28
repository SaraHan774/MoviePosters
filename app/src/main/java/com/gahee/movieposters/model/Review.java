package com.gahee.movieposters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable {

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("url")
    @Expose
    private String reviewUrl;

    public Review(String author, String content, String reviewUrl) {
        this.author = author;
        this.content = content;
        this.reviewUrl = reviewUrl;
    }

    protected Review(Parcel in) {
        author = in.readString();
        content = in.readString();
        reviewUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(reviewUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }


}
