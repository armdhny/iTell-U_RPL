package com.example.ari.itellu.admin.pertanyaan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ari.itellu.R;

import java.util.List;

/**
 * Created by Tavs on 09/05/2018.
 */

public class adapterKomen extends RecyclerView.Adapter<adapterKomen.KomenHolder> {

    private List<mKomen> komenList;


    public class KomenHolder extends RecyclerView.ViewHolder {
        public TextView namaKomenTV, commentTV;

        public KomenHolder(View itemView) {
            super(itemView);
            namaKomenTV = itemView.findViewById(R.id.tvNamaKomen);
            commentTV = itemView.findViewById(R.id.tvKomentar);
        }
    }

    public adapterKomen(List<mKomen> komenList) {
        this.komenList = komenList;
    }

    @Override
    public KomenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_comment, parent, false);
        return new KomenHolder(view);
    }

    @Override
    public void onBindViewHolder(KomenHolder holder, int position) {
        mKomen komen = komenList.get(position);
        holder.namaKomenTV.setText(komen.getName());
        holder.commentTV.setText(komen.getComment());

    }

    @Override
    public int getItemCount() {
        return komenList.size();
    }


}
