package com.nicolasrodf.tmdbclientjava;

import android.app.Application;

import com.nicolasrodf.tmdbclientjava.di.DaggerMovieComponent;
import com.nicolasrodf.tmdbclientjava.di.MovieComponent;

public class App extends Application {
    private static App app;
    private MovieComponent movieComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        movieComponent = DaggerMovieComponent.create();
    }

    public static App getApp() {
        return app;
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
