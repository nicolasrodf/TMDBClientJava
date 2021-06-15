package com.nicolasrodf.tmdbclientjava.service;

import com.nicolasrodf.tmdbclientjava.model.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String apiKey);
}
