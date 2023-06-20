package com.example.animeapi.Interface;

import com.example.animeapi.model.AnimeData;
import com.example.animeapi.model.AnimeResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JikanApi {


    interface view {
        void showAnimeData(List<AnimeData> animeList);

        void showError(String message);
    }



    interface Presenter {
        @GET("anime")
        Call<AnimeResponse> getAnime(@Query("q") String title);

        // Call<AnimeResponse> getAnime(@Query("limit") int limit, @Query("offset") int offset);
    }

    interface PresenterTop{
        @GET("anime")
        Call<AnimeResponse> getAnimeTop(@Query("order_by") String orderBy);
    }

    interface PresenterCategory{
        @GET("anime")
        Call<AnimeResponse> getAnimeCategory(@Query("type") String category);
    }

}

