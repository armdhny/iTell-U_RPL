package com.example.ari.itellu.admin.Event;

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
 * Created by Tavs on 07/05/2018.
 */

public class adapterEvent extends RecyclerView.Adapter<adapterEvent.EventHolder> {


    private List<mEvent> eventList;
    private Context context;

    public class EventHolder extends RecyclerView.ViewHolder {
        public TextView tvNameEvent, tvDateEvent;
        public ImageView imgPhotoEvent;
        public CardView mcvPhotoEvent;


        public EventHolder(View view) {
            super(view);
            tvNameEvent = (TextView) view.findViewById(R.id.titleEvent);
            tvDateEvent = (TextView) view.findViewById(R.id.dateEvent);
            imgPhotoEvent = (ImageView) view.findViewById(R.id.photoIMGEvent);
            mcvPhotoEvent = (CardView) view.findViewById(R.id.cvEvent);
        }
    }

    public adapterEvent(List<mEvent> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }


    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventHolder holder, int position) {
        Log.d("AdapterEvent", "Menuju item yang di klik");
        final mEvent event = eventList.get(position);
        holder.tvNameEvent.setText(event.getNamaEvent());
        holder.tvDateEvent.setText(event.getTglEvent());

        Picasso.get().load(event.getImage_url()).into(holder.imgPhotoEvent);
        holder.mcvPhotoEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mcvPhotoEvent", "Event Detail");
                Intent lur = new Intent(context, EventDetailActivity.class);
                lur.putExtra("eventData", event);
                context.startActivity(lur);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


}
