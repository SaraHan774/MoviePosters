package com.gahee.movieposters.data.database;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRoomViewModel extends AndroidViewModel {

    private static final String TAG = "MyRoomViewModel";
    private final MyRoomRepo myRoomRepo;

    public MyRoomViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "MyRoomViewModel: constructor running ... ");
        myRoomRepo = new MyRoomRepo(application);
    }

    public LiveData<List<LikedMovie>> getLikeMoviesLiveDataFromRepo(){
        return myRoomRepo.getLikedMoviesLiveDataFromDB();
    }

    public void insertLikedMovieViaViewModel(LikedMovie likedMovie){
        myRoomRepo.insertLikedMovie(likedMovie);
    }

    public void deleteLikedMovieByIdViaViewModel(int movieId){
        myRoomRepo.deleteLikedMovieById(movieId);
    }

    public void updateCommentByMovieIdViaViewModel(int movieId, String comment){
        myRoomRepo.updateCommentByMovieId(movieId, comment);
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }
}
