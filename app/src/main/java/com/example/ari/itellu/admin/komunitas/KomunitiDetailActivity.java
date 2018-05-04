package com.example.ari.itellu.admin.komunitas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ari.itellu.R;
import com.squareup.picasso.Picasso;

public class KomunitiDetailActivity extends AppCompatActivity {
    private ImageView mKomDetailFoto;
    private TextView mKomDetailNama, mKomDetailDeskrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komuniti_detail);
        mKomDetailFoto = (ImageView) findViewById(R.id.imgDetailKomuniti);
        mKomDetailNama = (TextView) findViewById(R.id.namaDetailKom);
        mKomDetailDeskrip = (TextView) findViewById(R.id.isiDeskripsiDetailKom);

        loadIntent();
    }

    mKomunitas komunitas;

    private void loadIntent() {
        if (getIntent().getExtras() != null) {
            komunitas = (mKomunitas) getIntent().getSerializableExtra("komData");
            Picasso.get().load(komunitas.getImage_url()).into(mKomDetailFoto);
            mKomDetailNama.setText(komunitas.getNamaKomunitas());
            mKomDetailDeskrip.setText(komunitas.getDeskripsi());
            setTitle(komunitas.getNamaKomunitas());
        }
    }
}
