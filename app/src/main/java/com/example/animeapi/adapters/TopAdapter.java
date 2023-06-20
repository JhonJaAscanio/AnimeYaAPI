package com.example.animeapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.example.animeapi.R;
import com.example.animeapi.model.AnimeData;
import com.example.animeapi.view.DetailAnime;
import com.squareup.picasso.Picasso;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private List<AnimeData> dataset;
    private Context context;

    public TopAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_top, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnimeData p = dataset.get(position);
        String imageUrl = p.getImages().getJpg().getImage_url();
        Picasso.get().load(imageUrl).into(holder.imAnime);
        holder.tvTitulo.setText(p.getTitle());
        String scor = String.valueOf(p.getScore());
        holder.tvScoreL.setText(scor);

        holder.llItemAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDetalleAnime(p,imageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateList(List<AnimeData> newList) {
        dataset.clear();
        dataset.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imAnime;
        private TextView tvTitulo,tvScoreL;
        private LinearLayout llItemAnime;
        private CardView cvImgAnime;

        public ViewHolder(View itemView) {
            super(itemView);
            imAnime = itemView.findViewById(R.id.ivAnime);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvScoreL = itemView.findViewById(R.id.tvScoreL);
            llItemAnime = itemView.findViewById(R.id.llItemAnime);
            cvImgAnime = itemView.findViewById(R.id.cvImgAnime);
        }
    }


    private void abrirDetalleAnime(AnimeData modeloItem, String url) {
        Intent intent = new Intent(context, DetailAnime.class);
        intent.putExtra("AnimeData", modeloItem);
        context.startActivity(intent);
    }

}