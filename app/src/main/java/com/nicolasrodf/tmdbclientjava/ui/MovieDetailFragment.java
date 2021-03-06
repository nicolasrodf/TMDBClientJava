package com.nicolasrodf.tmdbclientjava.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.databinding.FragmentMovieDetailBinding;
import com.nicolasrodf.tmdbclientjava.data.model.Movie;

public class MovieDetailFragment extends ParentFragment {

    private Movie movie;
    private FragmentMovieDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false);
        movie = MovieDetailFragmentArgs.fromBundle(getArguments()).getMovie();
        getMainActivity().setTitle(movie.getTitle());
        loadMovieData();
        return binding.getRoot();
    }

    private void loadMovieData() {
        Glide.with(getCurrentActivity())
                .load(getString(R.string.image_url)+movie.getPosterPath())
                .into(binding.ivMovieLarge);
        binding.tvMovieOverview.setText(movie.getOverview());
    }
}