package com.nicolasrodf.tmdbclientjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.nicolasrodf.tmdbclientjava.data.datasource.MovieDataSource;
import com.nicolasrodf.tmdbclientjava.data.factory.MovieDataSourceFactory;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.data.repository.MovieRepository;
import com.nicolasrodf.tmdbclientjava.service.MovieDataService;
import com.nicolasrodf.tmdbclientjava.service.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListLiveData;

    public MainActivityViewModel(@NonNull @NotNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
        MovieDataService movieDataService = RetrofitInstance.getMovieDataService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService);
        movieDataSourceLiveData = factory.getMovieDataSourceMutableLiveData();
        //
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = new LivePagedListBuilder<Integer,Movie>(factory,config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMoviesInRepository();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}