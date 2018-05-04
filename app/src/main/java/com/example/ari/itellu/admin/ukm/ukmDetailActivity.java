package com.example.ari.itellu.admin.ukm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ari.itellu.R;
import com.squareup.picasso.Picasso;

public class ukmDetailActivity extends AppCompatActivity {
    private ImageView mUkmDetailFoto;
    private TextView mUkmDetailNama, mUkmDetailDeskrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukm_detail);
        mUkmDetailFoto = (ImageView) findViewById(R.id.imgDetailFoto);
        mUkmDetailNama = (TextView) findViewById(R.id.namaUkmDetail);
        mUkmDetailDeskrip = (TextView) findViewById(R.id.isiDeskripsiDetail);

        loadIntent();

    }

    mUkm photo;

    private void loadIntent() {
        if (getIntent().getExtras() != null) {
            photo = (mUkm) getIntent().getSerializableExtra("photoData");
            Picasso.get().load(photo.getImage_url()).into(mUkmDetailFoto);
            mUkmDetailNama.setText(photo.getNamaUkm());
            mUkmDetailDeskrip.setText(photo.getDeskripsi());
            setTitle(photo.getNamaUkm());

        }
    }


}
