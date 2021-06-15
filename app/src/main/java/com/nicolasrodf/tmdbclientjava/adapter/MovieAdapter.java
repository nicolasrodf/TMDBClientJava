package com.nicolasrodf.tmdbclientjava.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nicolasrodf.tmdbclientjava.databinding.ItemMovieBinding;
import com.nicolasrodf.tmdbclientjava.model.Movie;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private List<Movie> movies;
    private OnMovieListener onMovieListener;

    public MovieAdapter(Context context, List<Movie> movies, OnMovieListener onMovieListener) {
        this.context = context;
        this.movies = movies;
        this.onMovieListener = onMovieListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.binding.tvTitle.setText(movies.get(position).getOriginalTitle());
        holder.binding.tvRating.setText(Double.toString(movies.get(position).getVoteAverage()));

        String imagePath="https://image.tmdb.org/t/p/w500"+ movies.get(position).getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .into(holder.binding.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;
        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> onMovieListener.onMovieClick(movies.get(getBindingAdapterPosition())));
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public interface OnMovieListener{
        void onMovieClick(Movie movie);
    }
}

