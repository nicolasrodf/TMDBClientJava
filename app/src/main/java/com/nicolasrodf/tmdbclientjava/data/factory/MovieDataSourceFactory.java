package com.nicolasrodf.tmdbclientjava.data.factory;

import androidx.paging.DataSource;
import com.nicolasrodf.tmdbclientjava.data.datasource.MovieDataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    @Override
    public DataSource create() {
        return new MovieDataSource();
    }
}
