package com.azens1995.offlinefirstarchitecture;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azens1995.offlinefirstarchitecture.data.Movie;
import com.azens1995.offlinefirstarchitecture.data.MovieResponse;
import com.azens1995.offlinefirstarchitecture.data.TmdbResource;
import com.azens1995.offlinefirstarchitecture.data.local.MovieDao;
import com.azens1995.offlinefirstarchitecture.data.local.MovieDatabase;
import com.azens1995.offlinefirstarchitecture.utils.NetworkBoundResource;
import com.azens1995.offlinefirstarchitecture.utils.Resource;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class MoviesRepository {

    private static MoviesRepository INSTANCE;
    private MovieDatabase movieDatabase;
    private MovieDao movieDao;
    private static final String TAG = "MoviesRepository";
    private Executor executor = Executors.newSingleThreadExecutor();

    public MoviesRepository(Application application) {
        movieDatabase = MovieDatabase.getInstance(application.getApplicationContext());
        movieDao = movieDatabase.movieDao();
    }

    public static MoviesRepository getInstance(Application application){
        if (INSTANCE == null){
            INSTANCE = new MoviesRepository(application);
        }
        return INSTANCE;
    }

    public LiveData<Resource<List<Movie>>> getAllMoviesByNBR(String api){
        return new NetworkBoundResource<List<Movie>, MovieResponse>(executor) {
            @Override
            protected void saveCallResult(@NonNull MovieResponse item) {
                movieDao.insertMovies(item.getResults());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return movieDao.getAllMovies();
            }

            @NonNull
            @Override
            protected Call<MovieResponse> createCall() {
                return TmdbResource.getTmdbService().getAllTrendingMovies(api);
            }
        }.getAsLiveData();
    }

    public LiveData<List<Movie>> getAllMovies(String api){
        Call<MovieResponse> responseCall = TmdbResource.getTmdbService().getAllTrendingMovies(api);
        return getMoviesFromNetwork(responseCall);
    }

    private LiveData<List<Movie>> getMoviesFromNetwork(Call<MovieResponse> responseCall) {
        MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    movies.setValue(response.body().getResults());
                    Log.d(TAG, "onResponse: "+new Gson().toJson(response));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
            }
        });
        return movies;
    }
}
