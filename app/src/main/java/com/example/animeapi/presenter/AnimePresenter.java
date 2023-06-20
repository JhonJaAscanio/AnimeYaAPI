package com.example.animeapi.presenter;

import com.example.animeapi.Interface.JikanApi;
import com.example.animeapi.model.AnimeData;
import com.example.animeapi.model.AnimeResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimePresenter implements JikanApi.Presenter, JikanApi.PresenterTop,JikanApi.PresenterCategory{
    private JikanApi.view view;
    private Retrofit retrofit;

    public AnimePresenter(JikanApi.view view) {
        this.view = view;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public Call<AnimeResponse> getAnime(String animeName) {
        JikanApi.Presenter jikanApi = retrofit.create(JikanApi.Presenter.class);
        Call<AnimeResponse> animeCall = jikanApi.getAnime(animeName);
        animeCall.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful()) {
                    AnimeResponse animeResponse = response.body();
                    if (animeResponse != null) {
                        List<AnimeData> animeList = animeResponse.getData();
                        if (animeList != null && !animeList.isEmpty()) {
                            view.showAnimeData(animeList);
                        } else {
                            view.showError("AnimeData esta vacio");
                        }
                    } else {
                        view.showError("AnimeResponse esta vacio");
                    }
                } else {
                    view.showError("onResponse error: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                view.showError("onFailure: " + t.getMessage());
            }
        });
        return null;
    }


    @Override
    public Call<AnimeResponse> getAnimeTop(String orderBy) {
        JikanApi.PresenterTop jikanApi = retrofit.create(JikanApi.PresenterTop.class);
        Call<AnimeResponse> animeCall = jikanApi.getAnimeTop(orderBy);

        animeCall.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful()) {
                    AnimeResponse animeResponse = response.body();
                    if (animeResponse != null) {
                        List<AnimeData> animeList = animeResponse.getData();
                        if (animeList != null && !animeList.isEmpty()) {
                            view.showAnimeData(animeList);
                        } else {
                            view.showError("AnimeData esta vacio");
                        }
                    } else {
                        view.showError("AnimeResponse esta vacio");
                    }
                } else {
                    view.showError("onResponse error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                view.showError("onFailure: " + t.getMessage());
            }
        });

        return animeCall;
    }


    @Override
    public Call<AnimeResponse> getAnimeCategory(String category) {
        JikanApi.PresenterCategory jikanApi = retrofit.create(JikanApi.PresenterCategory.class);
        Call<AnimeResponse> animeCall = jikanApi.getAnimeCategory(category);

        animeCall.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful()) {
                    AnimeResponse animeResponse = response.body();
                    if (animeResponse != null) {
                        List<AnimeData> animeList = animeResponse.getData();
                        if (animeList != null && !animeList.isEmpty()) {
                            view.showAnimeData(animeList);
                        } else {
                            view.showError("AnimeData esta vacio");
                        }
                    } else {
                        view.showError("AnimeResponse esta vacio");
                    }
                } else {
                    view.showError("onResponse error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                view.showError("onFailure: " + t.getMessage());
            }
        });

        return animeCall;
    }
}
