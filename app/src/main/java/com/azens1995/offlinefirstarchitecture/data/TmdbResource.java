package com.azens1995.offlinefirstarchitecture.data;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class TmdbResource {

    public static volatile TmdbService tmdbService;

    public interface TmdbService {

        @GET("trending/all/day")
        Call<MovieResponse> getAllTrendingMovies(@Query("api_key") String api);
    }

    public static TmdbService getTmdbService(){
        if (tmdbService == null){
            synchronized (TmdbService.class){
                if (tmdbService == null){
                    tmdbService = ServiceGenerator.createService(TmdbService.class);
                }
            }
        }
        return tmdbService;
    }
}
