package com.nicolasrodf.tmdbclientjava.data.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.nicolasrodf.tmdbclientjava.data.datasource.MovieDataSource;
import com.nicolasrodf.tmdbclientjava.service.MovieDataService;

import org.jetbrains.annotations.NotNull;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieDataService);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
