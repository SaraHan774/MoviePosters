package com.gahee.movieposters.data.remote;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahee.movieposters.model.PopularResponse;
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

    public void fetchPopularMoviesFromRepo(){
        remoteRepo.fetchPopularMoviesAsync();
    }

    public void fetchTrailersFromRepo(String movieId){remoteRepo.fetchTrailersAsync(movieId);}
}
