package com.example.ari.itellu.admin.ukm;

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
 * Created by Tavs on 25/04/2018.
 */

public class ukmAdapter extends RecyclerView.Adapter<ukmAdapter.MyViewHolder>{

    private List<mUkm> ukmList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameUkm;
        public ImageView imgPhoto;
        public CardView cvPhoto;
        public MyViewHolder(View view) {
            super(view);
            tvNameUkm = (TextView) view.findViewById(R.id.titleTV);
            imgPhoto = (ImageView) view.findViewById(R.id.photoIMG);
            cvPhoto = (CardView) view.findViewById(R.id.cvPhoto);
        }
    }

    public ukmAdapter(List<mUkm> ukmList, Context context) {
        this.ukmList = ukmList;
        this.context = context;
    }




    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ukm, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final mUkm ukm = ukmList.get(position);
        holder.tvNameUkm.setText(ukm.getNamaUkm());

        Picasso.get().load(ukm.getImage_url()).into(holder.imgPhoto);
        holder.cvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Foto Detail"," Soon");
                Intent in = new Intent(context,ukmDetailActivity.class);
                in.putExtra("photoData",ukm);
                context.startActivity(in);



            }
        });

    }

    @Override
    public int getItemCount() {
        return ukmList.size();
    }


}
