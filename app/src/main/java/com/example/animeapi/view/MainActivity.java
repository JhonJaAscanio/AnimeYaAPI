package com.example.animeapi.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.amrdeveloper.lottiedialog.LottieDialog;
import com.example.animeapi.R;
import com.example.animeapi.Interface.JikanApi;
import com.example.animeapi.model.AnimeData;
import com.example.animeapi.adapters.AnimeAdapter;
import com.example.animeapi.presenter.AnimePresenter;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JikanApi.view {

    private RecyclerView recyclerViewVertical,recyclerViewHorizontal;
    private AnimeAdapter animeAdapter;
    private ImageCarousel carousel;
    private JikanApi.Presenter presenter;
    private LottieDialog dialog;
    private EditText etAnimeSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etAnimeSearch=findViewById(R.id.etAnimeSearch);
        carousel = findViewById(R.id.carousel);
        carousel.registerLifecycle(getLifecycle());
        recyclerViewHorizontal = findViewById(R.id.rvHorizontal);
        recyclerViewVertical = findViewById(R.id.rvVertical);
        animeAdapter = new AnimeAdapter(this);
        recyclerViewHorizontal.setAdapter(animeAdapter);
        recyclerViewVertical.setAdapter(animeAdapter);
        LinearLayoutManager layoutManagerHor = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewHorizontal.setLayoutManager(layoutManagerHor);
        recyclerViewVertical.setLayoutManager(layoutManager);
        presenter = new AnimePresenter(this);

        showProgressBar();
        presenter.getAnime("");
    }

    public void Search(View v){
        String filterText = etAnimeSearch.getText().toString();
        if(filterText.length()==0){
            presenter.getAnime("");
            recyclerViewHorizontal.setVisibility(View.VISIBLE);
            carousel.setVisibility(View.VISIBLE);
        }else{
            presenter.getAnime(filterText);
            recyclerViewHorizontal.setVisibility(View.GONE);
            carousel.setVisibility(View.GONE);
        }
        updateRecyclerView(new ArrayList<>());
    }

    private void updateRecyclerView(List<AnimeData> newList) {
        animeAdapter.updateList(newList);
        recyclerViewVertical.scrollToPosition(0);
    }

    @Override
    public void showAnimeData(List<AnimeData> animeList) {
        List<CarouselItem> list = new ArrayList<>();
        for (AnimeData data : animeList) {
            String imageUrl = data.getImages().getJpg().getImage_url();
            String titulo = data.getTitle();
            if (imageUrl != null) {
                updateRecyclerView(animeList);
                list.add(new CarouselItem(imageUrl,titulo));
                carousel.setData(list);

            }
        }
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

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro que desea salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}