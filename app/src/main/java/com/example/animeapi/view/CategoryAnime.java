package com.example.animeapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amrdeveloper.lottiedialog.LottieDialog;
import com.example.animeapi.Interface.JikanApi;
import com.example.animeapi.R;
import com.example.animeapi.model.AnimeData;
import com.example.animeapi.adapters.AnimeAdapter;
import com.example.animeapi.presenter.AnimePresenter;

import java.util.ArrayList;
import java.util.List;

public class CategoryAnime extends AppCompatActivity implements JikanApi.view{
    private RecyclerView rvTv,rvMovie,rvOva,rvSpecial,rvOna,rvMusic;
    private ImageView ivTv,ivMovie,ivOva,ivSpecial,ivOna,ivMusic;
    private LinearLayout llTv,llMovie,llOva,llSpecial,llOna,llMusic;
    private AnimeAdapter animeAdapter;
    private JikanApi.Presenter presenter;
    private LottieDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_anime);

        //INICIALIZACION IMAGEVIEW (FLECHAS)
        ivTv=findViewById(R.id.ivTv);
        ivMovie=findViewById(R.id.ivMovie);
        ivOva=findViewById(R.id.ivOva);
        ivSpecial=findViewById(R.id.ivSpecial);
        ivOna=findViewById(R.id.ivOna);
        ivMusic=findViewById(R.id.ivMusic);
        //INICIALIZACION DE LOS LINEARLAYOUT (BOTONES)
        llTv=findViewById(R.id.llTv);
        llMovie=findViewById(R.id.llMovie);
        llOva=findViewById(R.id.llOva);
        llSpecial=findViewById(R.id.llSpecial);
        llOna=findViewById(R.id.llOna);
        llMusic=findViewById(R.id.llMusic);
        //INICIALIZACION DE LOS RECYCLERVIEW
        rvTv=findViewById(R.id.rvTv);
        rvMovie=findViewById(R.id.rvMovie);
        rvOva=findViewById(R.id.rvOva);
        rvSpecial=findViewById(R.id.rvSpecial);
        rvOna=findViewById(R.id.rvOna);
        rvMusic=findViewById(R.id.rvMusic);
        //SE IMPLEMENTA EL ADAPTADOR
        animeAdapter = new AnimeAdapter(this);
        rvTv.setAdapter(animeAdapter);
        rvMovie.setAdapter(animeAdapter);
        rvOva.setAdapter(animeAdapter);
        rvSpecial.setAdapter(animeAdapter);
        rvOna.setAdapter(animeAdapter);
        rvMusic.setAdapter(animeAdapter);
        LinearLayoutManager layoutManagerTv = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManagerMovie = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManagerOva = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManagerSpecial = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManagerOna = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManagerMusic = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvTv.setLayoutManager(layoutManagerTv);
        rvMovie.setLayoutManager(layoutManagerMovie);
        rvOva.setLayoutManager(layoutManagerOva);
        rvSpecial.setLayoutManager(layoutManagerSpecial);
        rvOna.setLayoutManager(layoutManagerOna);
        rvMusic.setLayoutManager(layoutManagerMusic);
        presenter= new AnimePresenter(this);

        showProgressBar();
        presenter.getAnime("tv");
    }

    private void updateRecyclerView(List<AnimeData> newList) {
        animeAdapter.updateList(newList);
    }

    @Override
    public void showAnimeData(List<AnimeData> animeList) {

        updateRecyclerView(animeList);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error de conexión!!", Toast.LENGTH_SHORT).show();
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

    public void back(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void tv(View v){
        presenter.getAnime("tv");
        ivTv.setRotation(90);
        ivMovie.setRotation(0);
        ivOva.setRotation(0);
        ivSpecial.setRotation(0);
        ivOna.setRotation(0);
        ivMusic.setRotation(0);
        rvTv.setVisibility(View.VISIBLE);
        rvMovie.setVisibility(View.GONE);
        rvOva.setVisibility(View.GONE);
        rvSpecial.setVisibility(View.GONE);
        rvOna.setVisibility(View.GONE);
        rvMusic.setVisibility(View.GONE);
        updateRecyclerView(new ArrayList<>());
    }

    public void movie(View v){
        presenter.getAnime("movie");
        ivTv.setRotation(0);
        ivMovie.setRotation(90);
        ivOva.setRotation(0);
        ivSpecial.setRotation(0);
        ivOna.setRotation(0);
        ivMusic.setRotation(0);
        rvTv.setVisibility(View.GONE);
        rvMovie.setVisibility(View.VISIBLE);
        rvOva.setVisibility(View.GONE);
        rvSpecial.setVisibility(View.GONE);
        rvOna.setVisibility(View.GONE);
        rvMusic.setVisibility(View.GONE);
        updateRecyclerView(new ArrayList<>());
    }
    public void ova(View v){
        presenter.getAnime("ova");
        ivTv.setRotation(0);
        ivMovie.setRotation(0);
        ivOva.setRotation(90);
        ivSpecial.setRotation(0);
        ivOna.setRotation(0);
        ivMusic.setRotation(0);
        rvTv.setVisibility(View.GONE);
        rvMovie.setVisibility(View.GONE);
        rvOva.setVisibility(View.VISIBLE);
        rvSpecial.setVisibility(View.GONE);
        rvOna.setVisibility(View.GONE);
        rvMusic.setVisibility(View.GONE);
        updateRecyclerView(new ArrayList<>());
    }

    public void special(View v){
        presenter.getAnime("special");
        ivTv.setRotation(0);
        ivMovie.setRotation(0);
        ivOva.setRotation(0);
        ivSpecial.setRotation(90);
        ivOna.setRotation(0);
        ivMusic.setRotation(0);
        rvTv.setVisibility(View.GONE);
        rvMovie.setVisibility(View.GONE);
        rvOva.setVisibility(View.GONE);
        rvSpecial.setVisibility(View.VISIBLE);
        rvOna.setVisibility(View.GONE);
        rvMusic.setVisibility(View.GONE);
        updateRecyclerView(new ArrayList<>());
    }
    public void ona(View v){
        presenter.getAnime("ona");
        ivTv.setRotation(0);
        ivMovie.setRotation(0);
        ivOva.setRotation(0);
        ivSpecial.setRotation(0);
        ivOna.setRotation(90);
        ivMusic.setRotation(0);
        rvTv.setVisibility(View.GONE);
        rvMovie.setVisibility(View.GONE);
        rvOva.setVisibility(View.GONE);
        rvSpecial.setVisibility(View.GONE);
        rvOna.setVisibility(View.VISIBLE);
        rvMusic.setVisibility(View.GONE);
        updateRecyclerView(new ArrayList<>());
    }

    public void music(View v){
        presenter.getAnime("music");
        ivTv.setRotation(0);
        ivMovie.setRotation(0);
        ivOva.setRotation(0);
        ivSpecial.setRotation(0);
        ivOna.setRotation(0);
        ivMusic.setRotation(90);
        rvTv.setVisibility(View.GONE);
        rvMovie.setVisibility(View.GONE);
        rvOva.setVisibility(View.GONE);
        rvSpecial.setVisibility(View.GONE);
        rvOna.setVisibility(View.GONE);
        rvMusic.setVisibility(View.VISIBLE);
        updateRecyclerView(new ArrayList<>());
    }

}