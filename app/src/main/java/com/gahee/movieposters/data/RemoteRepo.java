package com.gahee.movieposters.data;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.gahee.movieposters.model.PopularResponse;

public class RemoteRepo {

    private MoviesClient moviesClient;
    private static RemoteRepo instance;

    public static RemoteRepo getInstance() {
        if(instance == null){
            instance = new RemoteRepo();
        }
        return instance;
    }

    public RemoteRepo(){
        moviesClient = MoviesClient.getInstance();
    }

    public void fetchPopularMoviesAsync(){
        new PopularMoviesAsync(moviesClient).execute();
    }

    public MutableLiveData<PopularResponse> getPopularMovieResponseLiveDataFromClient(){
        return moviesClient.getPopularMovieResponseLiveData();
    }

    class PopularMoviesAsync extends AsyncTask<Void, Void, Void> {

        MoviesClient moviesClient;

        private PopularMoviesAsync(MoviesClient m){
            moviesClient = m;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moviesClient.fetchPopularMoviesList();
            return null;
        }
    }

}
