package com.nicolasrodf.tmdbclientjava.di;

import com.nicolasrodf.tmdbclientjava.data.factory.MovieDataSourceFactory;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    MovieDataSourceFactory providesMovieDataSourceFactory(){
        return new MovieDataSourceFactory();
    }
}
