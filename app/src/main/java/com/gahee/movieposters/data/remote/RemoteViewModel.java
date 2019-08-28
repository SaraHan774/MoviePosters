package com.gahee.movieposters.data.remote;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahee.movieposters.model.PopularResponse;
import com.gahee.movieposters.model.ReviewResponse;
import com.gahee.movieposters.model.TrailerResponse;

public class RemoteViewModel extends ViewModel {

    private final RemoteRepo remoteRepo;

    public RemoteViewModel(){
        remoteRepo = RemoteRepo.getInstance();
    }

    public MutableLiveData<PopularResponse> getPopularMovieResponseLiveDataFromRepo(){
        return remoteRepo.getPopularMovieResponseLiveDataFromClient();
    }

    public MutableLiveData<TrailerResponse> getTrailerLiveDataFromRepo(){
        return remoteRepo.getTrailerLiveDataFromClient();
    }

    public MutableLiveData<ReviewResponse> getReviewsLiveDataFromRepo(){
        return remoteRepo.getReviewLiveDataFromClient();
    }

    public void fetchPopularMoviesFromRepo(){
        remoteRepo.fetchPopularMoviesAsync();
    }

    public void fetchTrailersFromRepo(String movieId){remoteRepo.fetchTrailersAsync(movieId);}

    public void fetchReviewsFromRepo(String movieId){
        Log.d("reviews", "fetchReviewsFromRepo: " + movieId);
        remoteRepo.fetchReviewsAsync(movieId);}

}
