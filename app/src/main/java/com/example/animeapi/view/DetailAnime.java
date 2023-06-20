package com.example.animeapi.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.animeapi.R;
import com.example.animeapi.model.AnimeData;
import com.squareup.picasso.Picasso;


public class DetailAnime extends AppCompatActivity {
    private String titulo,url,tituloJapones,tipo,fuente,rating,sinopsis;
    int id,episodios,rango,year;
    double puntaje;
    private ImageView ivPortada;
    private TextView tvTitulo,tvIdiomaJ,tvTipo,tvFuente,tvEpisodio,tvRaiting,tvPuntaje,tvRango,tvYear,tvSinopsis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);
        iniComponet();
    }



    public void iniComponet(){

        ivPortada=findViewById(R.id.ivPortada);
        tvTitulo=(TextView) findViewById(R.id.tvTituloD);
        tvIdiomaJ=(TextView) findViewById(R.id.tvIdiomaJ);
        tvTipo=(TextView) findViewById(R.id.tvTipo);
        tvFuente=(TextView) findViewById(R.id.tvFuente);
        tvEpisodio=(TextView) findViewById(R.id.tvEpisodio);
        tvRaiting=(TextView) findViewById(R.id.tvRaiting);
        tvPuntaje=(TextView) findViewById(R.id.tvPuntaje);
        tvRango=(TextView) findViewById(R.id.tvRango);
        tvYear=(TextView) findViewById(R.id.tvYear);
        tvSinopsis=(TextView) findViewById(R.id.tvSinopsis);


        Bundle bundle = getIntent().getExtras();
        AnimeData animeData = (AnimeData) getIntent().getSerializableExtra("AnimeData");

        id=animeData.getMal_id();
        titulo = animeData.getTitle();
        url=animeData.getImages().getJpg().getImage_url();
        tituloJapones=animeData.getTitle_japanese();
        tipo=animeData.getType();
        fuente=animeData.getSource();
        episodios=animeData.getEpisodes();
        rating=animeData.getRating();
        puntaje=animeData.getScore();
        rango=animeData.getRank();
        year=animeData.getYear();
        sinopsis=animeData.getSynopsis();

        tvTitulo.setText(titulo);
        tvIdiomaJ.setText(tituloJapones);
        tvTipo.setText("Tipo: "+tipo);
        tvFuente.setText("Fuente: "+fuente);
        tvEpisodio.setText("Episodios: "+episodios);
        tvRaiting.setText("Raiting: "+rating);
        tvPuntaje.setText("Puntaje: "+puntaje);
        tvRango.setText("Rango: "+rango);
        tvYear.setText("Año: "+year);
        tvSinopsis.setText(sinopsis);

        Picasso.get().load(url).into(ivPortada);
    }

    public void back(View v){
        finish();
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
        finish();
    }
}
