package com.azens1995.offlinefirstarchitecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.azens1995.offlinefirstarchitecture.data.Movie;
import com.azens1995.offlinefirstarchitecture.databinding.ListItemMovieBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemMovieBinding movieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bindMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateMovie(List<Movie> movies){
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ListItemMovieBinding listItemMovieBinding;
        public MovieViewHolder(@NonNull ListItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            this.listItemMovieBinding = movieBinding;
        }

        private void bindMovie(Movie movie){
            listItemMovieBinding.setMovie(movie);
        }
    }
}
