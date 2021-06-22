package com.nicolasrodf.tmdbclientjava.service;

import com.nicolasrodf.tmdbclientjava.data.model.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieIface {

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") Integer page);
}
