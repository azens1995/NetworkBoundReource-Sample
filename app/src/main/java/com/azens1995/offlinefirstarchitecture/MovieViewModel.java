package com.azens1995.offlinefirstarchitecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.azens1995.offlinefirstarchitecture.data.Movie;
import com.azens1995.offlinefirstarchitecture.utils.Resource;

import java.util.List;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class MovieViewModel extends AndroidViewModel {
    private MoviesRepository moviesRepository;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = MoviesRepository.getInstance(application);
    }

    public LiveData<List<Movie>> getAllMovies(String apiKey){
        return moviesRepository.getAllMovies(apiKey);
    }

    public LiveData<Resource<List<Movie>>> getAllMoviesByNBR(String apiKey){
        return moviesRepository.getAllMoviesByNBR(apiKey);
    }

}
