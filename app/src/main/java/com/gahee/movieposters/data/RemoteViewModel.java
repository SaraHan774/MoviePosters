package com.gahee.movieposters.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahee.movieposters.model.PopularResponse;

public class RemoteViewModel extends ViewModel {

    private final RemoteRepo remoteRepo;

    public RemoteViewModel(){
        remoteRepo = RemoteRepo.getInstance();
    }

    public MutableLiveData<PopularResponse> getPopularMovieResponseLiveDataFromRepo(){
        return remoteRepo.getPopularMovieResponseLiveDataFromClient();
    }

    public void fetchPopularMoviesFromRepo(){
        remoteRepo.fetchPopularMoviesAsync();
    }

}
