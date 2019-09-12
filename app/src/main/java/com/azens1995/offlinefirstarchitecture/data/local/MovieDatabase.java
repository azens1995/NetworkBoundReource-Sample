package com.azens1995.offlinefirstarchitecture.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.azens1995.offlinefirstarchitecture.AppConfig;
import com.azens1995.offlinefirstarchitecture.data.Movie;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase INSTANCE;
    public abstract MovieDao movieDao();


    public static MovieDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MovieDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, MovieDatabase.class,
                            AppConfig.MOVIE_DB).build();
                }
            }
        }
        return INSTANCE;
    }
}
