package com.gahee.movieposters.data;

import com.gahee.movieposters.model.PopularResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MoviesService {

    @GET("discover/movie")
    Call<PopularResponse> listPopularMovies(@Query("api_key") String api_key, @QueryMap Map<String, String> queries);

}
