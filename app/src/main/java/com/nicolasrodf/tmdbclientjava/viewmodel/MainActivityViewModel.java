package com.nicolasrodf.tmdbclientjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.nicolasrodf.tmdbclientjava.data.factory.MovieDataSourceFactory;
import com.nicolasrodf.tmdbclientjava.data.model.Movie;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<PagedList<Movie>> pagedListLiveData;

    public MainActivityViewModel(MovieDataSourceFactory movieDataSourceFactory) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        Executor executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = new LivePagedListBuilder<Integer,Movie>(movieDataSourceFactory,config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}