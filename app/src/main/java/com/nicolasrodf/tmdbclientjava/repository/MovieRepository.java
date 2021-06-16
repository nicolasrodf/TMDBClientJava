package com.nicolasrodf.tmdbclientjava.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.model.MovieDbResponse;
import com.nicolasrodf.tmdbclientjava.service.MovieDataService;
import com.nicolasrodf.tmdbclientjava.service.RetrofitInstance;
import com.nicolasrodf.tmdbclientjava.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepository {
    private static final String TAG = "MovieRepository";

    private MutableLiveData<List<Movie>> moviesInRepository = new MutableLiveData<>();
    private static MovieRepository movieRepository;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }


    public MutableLiveData<List<Movie>> getMoviesInRepository() {

        MovieDataService movieDataService = RetrofitInstance.getMovieDataService();
        Call<MovieDbResponse> movieDbResponseCall = movieDataService.getPopularMovies(Constants.API_KEY);
        movieDbResponseCall.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                if(response.isSuccessful()){
                    moviesInRepository.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });


        return moviesInRepository;
    }
}

