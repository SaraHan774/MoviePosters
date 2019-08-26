package com.gahee.movieposters.data;

import com.gahee.movieposters.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceBuilder {

    private static final Retrofit retrofit_popular_movies = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final MoviesService MOVIE_SERVICE = retrofit_popular_movies.create(MoviesService.class);

    public static MoviesService getMovieService() {
        return MOVIE_SERVICE;
    }

}
