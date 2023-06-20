package com.example.animeapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amrdeveloper.lottiedialog.LottieDialog;
import com.example.animeapi.Interface.JikanApi;
import com.example.animeapi.R;
import com.example.animeapi.model.AnimeData;
import com.example.animeapi.presenter.AnimePresenter;
import com.example.animeapi.adapters.TopAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopAnime extends AppCompatActivity implements JikanApi.view {

    private RecyclerView recyclerViewTopAnime;
    private TopAdapter animeAdapter;
    private JikanApi.PresenterTop presenterTop;
    private LottieDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_anime);

        recyclerViewTopAnime=findViewById(R.id.rvTopAnime);
        animeAdapter = new TopAdapter(this);
        recyclerViewTopAnime.setAdapter(animeAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerViewTopAnime.setLayoutManager(layoutManager);

        showProgressBar();
        presenterTop = new AnimePresenter(this);
        presenterTop.getAnimeTop("score");
    }

    private void updateRecyclerView(List<AnimeData> newList) {
        animeAdapter.updateList(newList);
    }

    @Override
    public void showAnimeData(List<AnimeData> animeList) {

        Collections.sort(animeList, new Comparator<AnimeData>() {
            @Override
            public int compare(AnimeData anime1, AnimeData anime2) {
                // Comparar los puntajes en orden descendente
                return Double.compare(anime2.getScore(), anime1.getScore());
            }
        });
        updateRecyclerView(animeList);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    public void showProgressBar(){
        dialog = new LottieDialog(this)
                .setAnimation("progress.json")
                .setAutoPlayAnimation(true)
                .setDialogHeightPercentage(.2f)
                .setDialogBackground(Color.TRANSPARENT)
                .setAnimationRepeatCount(LottieDialog.INFINITE)
                .setMessage("Loading...");
        dialog.show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error de conexión!", Toast.LENGTH_SHORT).show();
    }

    public void home(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void top(MenuItem item) {
        Intent intent = new Intent(this, TopAnime.class);
        startActivity(intent);
    }
    public void category(MenuItem item) {
        Intent intent = new Intent(this, CategoryAnime.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void back(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}