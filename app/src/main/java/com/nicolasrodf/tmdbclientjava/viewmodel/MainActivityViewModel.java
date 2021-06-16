package com.nicolasrodf.tmdbclientjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.repository.MovieRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull @NotNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMoviesInRepository();
    }


}