package com.example.ari.itellu.admin.komunitas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ari.itellu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tavs on 04/05/2018.
 */

public class komunitiAdapter extends RecyclerView.Adapter<komunitiAdapter.KomunitiHolder> {

    private List<mKomunitas> komunitasList;
    private Context context;


    public class KomunitiHolder extends RecyclerView.ViewHolder {
        public TextView tvNameKomuniti;
        public ImageView imgPhotoKomuniti;
        public CardView mcvPhotoKomuniti;

        public KomunitiHolder(View view) {
            super(view);
            tvNameKomuniti = (TextView) view.findViewById(R.id.titleKomuniti);
            imgPhotoKomuniti = (ImageView) view.findViewById(R.id.photoIMGKomuniti);
            mcvPhotoKomuniti = (CardView) view.findViewById(R.id.cvKomuniti);
        }

    }

    public komunitiAdapter(List<mKomunitas> komunitasList, Context context) {
        this.komunitasList = komunitasList;
        this.context = context;
    }

    @Override
    public KomunitiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_komuniti, parent, false);

        return new KomunitiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final KomunitiHolder holder, int position) {
        Log.d("Test", "Menuju item yang di klik");
        final mKomunitas komunitas = komunitasList.get(position);
        holder.tvNameKomuniti.setText(komunitas.getNamaKomunitas());

        Picasso.get().load(komunitas.getImage_url()).into(holder.imgPhotoKomuniti);
        holder.mcvPhotoKomuniti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Komuniti Foto Detail", "Soon");
                Intent ler = new Intent(context, KomunitiDetailActivity.class);
                ler.putExtra("komData", komunitas);
                context.startActivity(ler);
            }
        });
    }

    @Override
    public int getItemCount() {
        return komunitasList.size();
    }


}
