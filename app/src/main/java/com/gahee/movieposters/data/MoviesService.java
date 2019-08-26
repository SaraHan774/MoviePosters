package com.gahee.movieposters.data;

import com.gahee.movieposters.model.PopularResponse;
import com.gahee.movieposters.model.TrailerResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MoviesService {

    @GET("discover/movie")
    Call<PopularResponse> listPopularMovies(@Query("api_key") String api_key, @QueryMap Map<String, String> queries);

//    https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> listTrailers(@Path("movie_id") String movieId
                                        , @Query("api_key") String api_key
                                            , @Query("language") String language);

}
