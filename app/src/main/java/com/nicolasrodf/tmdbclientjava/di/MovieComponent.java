package com.nicolasrodf.tmdbclientjava.di;

import com.nicolasrodf.tmdbclientjava.ui.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface MovieComponent {
    void inject(MovieListFragment movieListFragment);
}
