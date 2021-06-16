package com.nicolasrodf.tmdbclientjava.data.datasource;

import android.util.Log;

import androidx.paging.PageKeyedDataSource;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.model.MovieDbResponse;
import com.nicolasrodf.tmdbclientjava.service.MovieDataService;
import com.nicolasrodf.tmdbclientjava.service.RetrofitInstance;
import com.nicolasrodf.tmdbclientjava.util.Constants;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    private static final String TAG = "MovieDataSource";

    private MovieDataService movieDataService;

    public MovieDataSource(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }


    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Movie> loadCallback) {
        MovieDataService movieDataService = RetrofitInstance.getMovieDataService();
        Call<MovieDbResponse> movieDbResponseCall = movieDataService.getPopularMoviesWithPaging(Constants.API_KEY,loadParams.key);
        movieDbResponseCall.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                if(response.isSuccessful()){
                    loadCallback.onResult(response.body().getMovies(),loadParams.key+1);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Movie> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams, @NotNull LoadInitialCallback<Integer, Movie> loadInitialCallback) {
        MovieDataService movieDataService = RetrofitInstance.getMovieDataService();
        Call<MovieDbResponse> movieDbResponseCall = movieDataService.getPopularMoviesWithPaging(Constants.API_KEY,1);
        movieDbResponseCall.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                if(response.isSuccessful()){
                    loadInitialCallback.onResult(response.body().getMovies(),null,2);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
