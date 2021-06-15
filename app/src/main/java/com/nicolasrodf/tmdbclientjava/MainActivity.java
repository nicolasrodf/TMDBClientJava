package com.nicolasrodf.tmdbclientjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.nicolasrodf.tmdbclientjava.databinding.ActivityMainBinding;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.ui.MovieDetailFragment;
import com.nicolasrodf.tmdbclientjava.ui.MovieListFragment;
import com.nicolasrodf.tmdbclientjava.util.Helper;

public class MainActivity extends AppCompatActivity {

    private MovieListFragment movieListFragment;
    private MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        actionNewMovieListFragment();
    }

    public void actionNewMovieListFragment() {
        if (movieListFragment == null) {
            movieListFragment = MovieListFragment.newInstance();
            Helper.replaceFragment(getSupportFragmentManager(), R.id.fl_container, movieListFragment);
        }
    }

    public void actionNewMovieDetailFragment(Movie movie) {
        if (movieDetailFragment == null) {
            movieDetailFragment = MovieDetailFragment.newInstance(movie);
            Helper.addFragmentAnim(getSupportFragmentManager(), R.id.fl_container, movieDetailFragment);
        }
    }

    public void onNavigationClickAction() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (f instanceof MovieDetailFragment) {
            actionCloseMovieDetailFragment();
        }
    }

    public void actionCloseMovieDetailFragment() {
        if (movieDetailFragment != null) {
            Helper.removeFragmentAnim(getSupportFragmentManager(), movieDetailFragment);
            movieDetailFragment = null;
        }
    }

    @Override
    public void onBackPressed() {
        onNavigationClickAction();
    }
}

