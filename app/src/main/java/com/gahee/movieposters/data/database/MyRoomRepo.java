package com.gahee.movieposters.data.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyRoomRepo {

    private static final String TAG = "MyRoomRepo";

    private final MyRoomDaos daos;
    private static LikedMovie likedMovie;

    public MyRoomRepo(Context context){
        //access database from repository
        //hence the name "Data Access Object"
        MyRoomDB myRoomDB = MyRoomDB.getDatabase(context);
        daos = myRoomDB.daos();
    }

    public LiveData<List<LikedMovie>> getLikedMoviesLiveDataFromDB(){
        try {
            return new LoadAsync(daos).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertLikedMovie(LikedMovie likedMovie){
        Log.d(TAG, "insertLikedMovie: " + likedMovie.getTitle());
        new InsertLikedMovieAsync(daos).execute(likedMovie);
    }

    public void deleteLikedMovieById(int movieId){
        Log.d(TAG, "deleteLikedMovieById: " + movieId);
        new DeleteLikedMovieAsync(daos).execute(movieId);
    }

    public static class InsertLikedMovieAsync extends AsyncTask<LikedMovie, Void, Void>{
        private final MyRoomDaos daos;

        InsertLikedMovieAsync(MyRoomDaos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(LikedMovie... likedMovies) {
            Log.d(TAG, "doInBackground: inserting " + likedMovies + " in the database...");
            daos.insertMovie(likedMovies[0]);
            return null;
        }
    }

    public static class DeleteLikedMovieAsync extends AsyncTask<Integer, Void, Void>{
        private final MyRoomDaos daos;

        DeleteLikedMovieAsync(MyRoomDaos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d(TAG, "doInBackground: deleting from the database...");
            daos.deleteByMovieId(integers[0]);
            return null;
        }
    }


    public static class LoadAsync extends AsyncTask<Void, Void, LiveData<List<LikedMovie>>>{
        private final MyRoomDaos daos;

        LoadAsync(MyRoomDaos daos){
            this.daos = daos;
        }

        @Override
        protected LiveData<List<LikedMovie>> doInBackground(Void... voids) {
            return daos.loadAllLikedMovies();
        }
    }
}