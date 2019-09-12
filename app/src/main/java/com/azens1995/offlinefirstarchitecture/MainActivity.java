package com.azens1995.offlinefirstarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.azens1995.offlinefirstarchitecture.data.Movie;
import com.azens1995.offlinefirstarchitecture.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList = new ArrayList<>();
    private MovieViewModel movieViewModel;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mainBinding.moviesList.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(movieList);
        mainBinding.moviesList.setAdapter(movieAdapter);

        /*movieViewModel.getAllMovies(AppConfig.API_KEY).observe(this, movieList ->{
            if (movieList != null){
                mainBinding.progressBar.setVisibility(View.GONE);
                movieAdapter.updateMovie(movieList);
            }
        });*/

        mainBinding.progressBar.setVisibility(View.VISIBLE);
        // using the network bound resource
        movieViewModel.getAllMoviesByNBR(AppConfig.API_KEY).observe(this, movieResource->{
            //Log.d(TAG, "onCreate: "+new Gson().toJson(movieResource));
            if (movieResource != null){
                switch (movieResource.status){
                    case ERROR:
                        mainBinding.errorText.setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        mainBinding.errorText.setVisibility(View.GONE);
                        mainBinding.progressBar.setVisibility(View.GONE);
                        break;
                    case SUCCESS:
                        Log.d(TAG, "onCreate: SUCCESS--->>>"+new Gson().toJson(movieResource.data));
                        if (movieResource.data != null && movieResource.data.size()>0){
                            mainBinding.errorText.setVisibility(View.GONE);
                            mainBinding.progressBar.setVisibility(View.GONE);
                            movieAdapter.updateMovie(movieResource.data);
                        }else {
                            mainBinding.errorText.setVisibility(View.VISIBLE);
                        }
                        break;
                    default:
                        mainBinding.progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}
