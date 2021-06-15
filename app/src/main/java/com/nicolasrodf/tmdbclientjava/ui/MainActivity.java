package com.nicolasrodf.tmdbclientjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.databinding.ActivityMainBinding;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.util.Helper;

public class MainActivity extends AppCompatActivity {

    private MovieListFragment movieListFragment;
    private MovieDetailFragment movieDetailFragment;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
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
            showBackToolbarButtonAndCollapsingToolbar();
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
            refreshToolbar();
        }
    }

    private void refreshToolbar(){
        setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void showBackToolbarButtonAndCollapsingToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onNavigationClickAction();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        onNavigationClickAction();
    }
}

