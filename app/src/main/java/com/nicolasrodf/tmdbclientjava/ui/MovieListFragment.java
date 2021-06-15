package com.nicolasrodf.tmdbclientjava.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.adapter.MovieAdapter;
import com.nicolasrodf.tmdbclientjava.databinding.FragmentMovieListBinding;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import com.nicolasrodf.tmdbclientjava.model.MovieDbResponse;
import com.nicolasrodf.tmdbclientjava.service.MovieDataService;
import com.nicolasrodf.tmdbclientjava.service.RetrofitInstance;
import com.nicolasrodf.tmdbclientjava.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends ParentFragment implements MovieAdapter.OnMovieListener {
    private static final String TAG = "MovieListFragment";

    private MovieAdapter movieAdapter;
    private FragmentMovieListBinding binding;

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater,container,false);
        initAdapter();
        getPopularMovies();
        setSwipeToRefresh();
        return binding.getRoot();

    }

    private void setSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener(() -> getPopularMovies());
    }

    private void getPopularMovies() {
        MovieDataService movieDataService = RetrofitInstance.getMovieDataService();
        Call<MovieDbResponse> movieDbResponseCall = movieDataService.getPopularMovies(Constants.API_KEY);
        movieDbResponseCall.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                movieAdapter.setMovies(response.body().getMovies());
                movieAdapter.notifyDataSetChanged();
                binding.swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void initAdapter() {
        movieAdapter = new MovieAdapter(getContext(),new ArrayList<>(),this);
        binding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.rvMovies.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        getMainActivity().actionNewMovieDetailFragment(movie);
    }
}