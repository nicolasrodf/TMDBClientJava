package com.nicolasrodf.tmdbclientjava.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nicolasrodf.tmdbclientjava.R;
import com.nicolasrodf.tmdbclientjava.databinding.ItemMovieBinding;
import com.nicolasrodf.tmdbclientjava.data.model.Movie;

public class MovieAdapter extends PagedListAdapter<Movie,MovieAdapter.MovieViewHolder>{

    private Context context;
    private OnMovieListener onMovieListener;

    public MovieAdapter(Context context, OnMovieListener onMovieListener) {
        super(Movie.CALLBACK);
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.binding.tvTitle.setText(getItem(position).getOriginalTitle());
        holder.binding.tvRating.setText(Double.toString(getItem(position).getVoteAverage()));

        String imagePath=context.getString(R.string.image_url)+ getItem(position).getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .into(holder.binding.ivMovie);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;
        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> onMovieListener.onMovieClick(getItem(getBindingAdapterPosition())));
        }
    }

    public interface OnMovieListener{
        void onMovieClick(Movie movie);
    }
}

