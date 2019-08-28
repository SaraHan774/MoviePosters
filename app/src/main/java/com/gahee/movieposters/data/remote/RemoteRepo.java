package com.gahee.movieposters.data.remote;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.gahee.movieposters.model.PopularResponse;
import com.gahee.movieposters.model.ReviewResponse;
import com.gahee.movieposters.model.TrailerResponse;

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

    public void fetchTrailersAsync(String movieId){new FetchTrailersAsync(moviesClient).execute(movieId);}

    public void fetchReviewsAsync(String movieId){new FetchReviewsAsync(moviesClient).execute(movieId);}

    public MutableLiveData<PopularResponse> getPopularMovieResponseLiveDataFromClient(){
        return moviesClient.getPopularMovieResponseLiveData();
    }

    public MutableLiveData<TrailerResponse> getTrailerLiveDataFromClient(){
        return moviesClient.getTrailerResponseLiveData();
    }

    public MutableLiveData<ReviewResponse> getReviewLiveDataFromClient(){
        return moviesClient.getReviewResponseMutableLiveData();
    }

    static class PopularMoviesAsync extends AsyncTask<Void, Void, Void> {

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

    static class FetchTrailersAsync extends AsyncTask<String, Void, Void> {

        MoviesClient moviesClient;

        private FetchTrailersAsync(MoviesClient m){
            moviesClient = m;
        }

        @Override
        protected Void doInBackground(String... strings) {
            moviesClient.fetchMovieTrailers(strings[0]);
            return null;
        }
    }

    static class FetchReviewsAsync extends AsyncTask<String, Void, Void>{
        MoviesClient moviesClient;

        private FetchReviewsAsync(MoviesClient m){
            moviesClient = m;
        }

        @Override
        protected Void doInBackground(String... strings) {
            moviesClient.fetchMovieReviews(strings[0]);
            return null;
        }
    }
}
