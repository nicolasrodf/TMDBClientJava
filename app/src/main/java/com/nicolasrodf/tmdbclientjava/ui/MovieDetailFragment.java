package com.nicolasrodf.tmdbclientjava.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.model.Movie;

public class MovieDetailFragment extends ParentFragment {

    private Movie movie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }
}