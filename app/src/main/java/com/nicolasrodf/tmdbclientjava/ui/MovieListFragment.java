package com.nicolasrodf.tmdbclientjava.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;
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
import com.nicolasrodf.tmdbclientjava.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends ParentFragment implements MovieAdapter.OnMovieListener {
    private static final String TAG = "MovieListFragment";

    private MovieAdapter movieAdapter;
    private FragmentMovieListBinding binding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater,container,false);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        initAdapter();
        getPopularMovies();
        setSwipeToRefresh();
        return binding.getRoot();

    }

    private void setSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener(() -> getPopularMovies());
    }

    private void getPopularMovies() {
        /*mainActivityViewModel.getAllMovies().observe(getMainActivity(), movies -> {
            movieAdapter.setMovies(movies);
            movieAdapter.notifyDataSetChanged();
            binding.swiperefresh.setRefreshing(false);
        });*/
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