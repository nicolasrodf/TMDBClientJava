package com.nicolasrodf.tmdbclientjava.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolasrodf.tmdbclientjava.App;
import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.adapter.MovieAdapter;
import com.nicolasrodf.tmdbclientjava.databinding.FragmentMovieListBinding;
import com.nicolasrodf.tmdbclientjava.data.model.Movie;
import com.nicolasrodf.tmdbclientjava.viewmodel.MainActivityViewModel;
import com.nicolasrodf.tmdbclientjava.viewmodel.MainActivityViewModelFactory;

import javax.inject.Inject;

public class MovieListFragment extends ParentFragment implements MovieAdapter.OnMovieListener {
    private static final String TAG = "MovieListFragment";

    private MovieAdapter movieAdapter;
    private FragmentMovieListBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater,container,false);
        App.getApp().getMovieComponent().inject(this);
        mainActivityViewModel = new ViewModelProvider(this,mainActivityViewModelFactory).get(MainActivityViewModel.class);
        initAdapter();
        getPopularMovies();
        setSwipeToRefresh();
        return binding.getRoot();

    }

    private void setSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener(() -> getPopularMovies());
    }

    private void getPopularMovies() {
        mainActivityViewModel.getPagedListLiveData().observe(getMainActivity(), movies -> {
            movieAdapter.submitList(movies);
            binding.swiperefresh.setRefreshing(false);
        });
    }

    private void initAdapter() {
        movieAdapter = new MovieAdapter(getContext(),this);
        binding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.rvMovies.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        getMainActivity().actionNewMovieDetailFragment(movie);
    }
}