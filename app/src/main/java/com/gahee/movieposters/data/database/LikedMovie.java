package com.gahee.movieposters.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "likedMoviesTable")
public class LikedMovie {

    @ColumnInfo(name = "movieId")
    private int movieId;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieTitle")
    private final String title;

    @ColumnInfo(name = "overview")
    private final String overview;

    @ColumnInfo(name = "posterPath")
    private final String posterPath;

    @ColumnInfo(name = "releaseDate")
    private final String releaseDate;

    @ColumnInfo(name = "voteAverage")
    private final float voteAverage;

    public LikedMovie(int movieId, @NonNull String title, String overview, String posterPath, String releaseDate, float voteAverage) {
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public int getMovieId() {
        return movieId;
    }

    @NonNull
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
