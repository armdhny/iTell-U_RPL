package com.example.ari.itellu.admin.Event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ari.itellu.R;
import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity {
    private ImageView mEventDetailFoto;
    private TextView mEventDetailNama, mEventDetailDate, mEventDetailDeskrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        mEventDetailFoto = (ImageView) findViewById(R.id.imgDetailEvent);
        mEventDetailNama = (TextView) findViewById(R.id.namaDetailEvent);
        mEventDetailDate = (TextView) findViewById(R.id.tglDetailEvent);
        mEventDetailDeskrip = (TextView) findViewById(R.id.isiDeskripsiDetailEvent);

        loadIntent();
    }

    mEvent event;

    private void loadIntent() {
        if (getIntent().getExtras() != null) {
            event = (mEvent) getIntent().getSerializableExtra("eventData");
            Picasso.get().load(event.getImage_url()).into(mEventDetailFoto);
            mEventDetailNama.setText(event.getNamaEvent());
            mEventDetailDate.setText(event.getTglEvent());
            mEventDetailDeskrip.setText(event.getDeskripsiEvent());
        }
    }
}
