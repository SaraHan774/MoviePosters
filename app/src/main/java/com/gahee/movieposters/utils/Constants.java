package com.gahee.movieposters.utils;

public class Constants {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500";
    //queries
    public static final String KEY_LANGUAGE = "language";
    public static final String EN = "en-US";
    public static final String KEY_SORT_BY = "sort_by";
    public static final String POPULARITY = "popularity.desc";
    public static final String KEY_PAGE = "page";
    public static final String PAGE = "1";

    //movie poster
    public static final int MAIN_MOVIE_POSTER_CORNER_RADIUS = 20;

    //intent
    public static final String PARCEL_KEY = "clickedMovieParcelKey";
    public static final String TO_COLLECTIONS_PARCEL_KEY = "toCollectionsParcelKey";
}
