package com.gahee.movieposters.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyRoomDaos {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(LikedMovie likedMovie);

    @Query("UPDATE likedMoviesTable SET comment = :comment WHERE movieId = :movieId")
    void updateCommentByMovieId(int movieId, String comment);

    @Query("DELETE FROM likedMoviesTable WHERE movieId = :movieId")
    void deleteByMovieId(int movieId);

    @Query("SELECT * FROM likedMoviesTable")
    LiveData<List<LikedMovie>> loadAllLikedMovies();

}
