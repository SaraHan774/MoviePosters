package com.gahee.movieposters.data;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gahee.movieposters.model.PopularMovie;
import com.gahee.movieposters.model.PopularResponse;
import com.gahee.movieposters.utils.Config;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gahee.movieposters.utils.Constants.EN;
import static com.gahee.movieposters.utils.Constants.KEY_LANGUAGE;
import static com.gahee.movieposters.utils.Constants.KEY_PAGE;
import static com.gahee.movieposters.utils.Constants.KEY_SORT_BY;
import static com.gahee.movieposters.utils.Constants.PAGE;
import static com.gahee.movieposters.utils.Constants.POPULARITY;

public class MoviesClient {

    public static final String RETROFIT_DEBUG = "retrofit";
    private static final String TAG = "MoviesClient";

    private MutableLiveData<PopularResponse> popularMovieResponseLiveData = new MutableLiveData<>();

    private HashMap<String, String> queries = new HashMap<>();
    private static MoviesClient instance;
    private MoviesService moviesService = RetrofitInstanceBuilder.getMovieService();


    public static MoviesClient getInstance(){
        if(instance == null){
            instance = new MoviesClient();
        }
        return instance;
    }

    public void fetchPopularMoviesList(){
        if(queries == null){
            appendQueries();
        }
        final Call<PopularResponse> popularMovies
                = moviesService.listPopularMovies(Config.API_KEY, queries);

        Callback<PopularResponse> movieCallback
                = new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
//                Log.d(RETROFIT_DEBUG, "on response : " + response.body().getResults());
//
//                for(PopularMovie popularMovie : response.body().getResults()){
//                    Log.d(RETROFIT_DEBUG, "title : " + popularMovie.getTitle());
//                }
                popularMovieResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                Log.d(RETROFIT_DEBUG, "on failure : " + t.getMessage());
            }
        };
        popularMovies.enqueue(movieCallback);
    }

    public MutableLiveData<PopularResponse> getPopularMovieResponseLiveData() {
        return popularMovieResponseLiveData;
    }

    private HashMap<String, String> appendQueries(){
        queries.put(KEY_LANGUAGE, EN);
        queries.put(KEY_SORT_BY, POPULARITY);
        queries.put(KEY_PAGE, PAGE);
        return queries;
    }


}
