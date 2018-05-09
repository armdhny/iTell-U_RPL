package com.example.ari.itellu.admin.pertanyaan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Tavs on 08/05/2018.
 */

public class adapterPertanyaan extends RecyclerView.Adapter<adapterPertanyaan.PertanyaanHolder> {

    private List<mPertanyaan> tanyaList;
    private Context context;

    public class PertanyaanHolder extends RecyclerView.ViewHolder {
        public TextView tvNameTanya, tvTitleTanya, tvCommentTanya;
        public CardView cvTanya;

        public PertanyaanHolder(View itemView) {
            super(itemView);
            tvNameTanya = itemView.findViewById(R.id.tvNamaTanya);
            tvTitleTanya = itemView.findViewById(R.id.titleTVtanya);
//            tvDescTanya = itemView.findViewById(R.id.tvDeskripsiTanya);
            tvCommentTanya = itemView.findViewById(R.id.tvComment);
            cvTanya = itemView.findViewById(R.id.cvTanya);

        }
    }

    public adapterPertanyaan(List<mPertanyaan> tanyaList, Context context) {
        this.tanyaList = tanyaList;
        this.context = context;
    }


    @Override
    public PertanyaanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tanya, parent, false);
        return new PertanyaanHolder(view);
    }

    int comment = 0;

    @Override
    public void onBindViewHolder(final PertanyaanHolder holder, int position) {
        final mPertanyaan tanya = tanyaList.get(position);
        holder.tvTitleTanya.setText(tanya.getJudulPertanyaan());
        holder.tvNameTanya.setText(tanya.getNamaPertanyaan());

        FirebaseC.refPertanyaan.child(tanya.getKey_pertanyaan()).child("commentList")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        comment = 0;

                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                            mKomen model = ds.getValue(mKomen.class);
                            comment++;
                        }
                        holder.tvCommentTanya.setText(comment + "");
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("", "Failed to read value.", error.toException());
                    }
                });

        holder.cvTanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, ActivityDetailTanya.class);
                in.putExtra("tanyaData", tanya);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tanyaList.size();
    }


}
