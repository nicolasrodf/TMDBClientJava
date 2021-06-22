package com.nicolasrodf.tmdbclientjava.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.nicolasrodf.tmdbclientjava.data.factory.MovieDataSourceFactory;
import org.jetbrains.annotations.NotNull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final MovieDataSourceFactory movieDataSourceFactory;

    @Inject
    public MainActivityViewModelFactory(MovieDataSourceFactory movieDataSourceFactory) {
        this.movieDataSourceFactory = movieDataSourceFactory;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(movieDataSourceFactory);
    }
}
