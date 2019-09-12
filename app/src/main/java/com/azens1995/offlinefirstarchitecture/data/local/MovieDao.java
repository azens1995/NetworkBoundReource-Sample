package com.azens1995.offlinefirstarchitecture.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.azens1995.offlinefirstarchitecture.data.Movie;
import com.azens1995.offlinefirstarchitecture.data.MovieResponse;

import java.util.List;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<Movie> movieResponse);

    @Query("SELECT * FROM movie_table")
    LiveData<List<Movie>> getAllMovies();

}
