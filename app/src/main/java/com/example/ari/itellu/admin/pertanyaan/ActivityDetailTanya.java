package com.example.ari.itellu.admin.pertanyaan;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityDetailTanya extends AppCompatActivity {
    private TextView mNamaDetailTanya, mTitleDetailTanya, mDescDetailTanya;
    private TextInputEditText mEtKomentar;
    private Button mBtnKirim;
    private RecyclerView mrvKomentar;
    private ArrayList<mKomen> listKomen;
    private adapterKomen mAdapterKomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanya);
        mNamaDetailTanya = (TextView) findViewById(R.id.namaDetailTanya);
        mTitleDetailTanya = (TextView) findViewById(R.id.titleDetailTanya);
        mDescDetailTanya = (TextView) findViewById(R.id.descDetailTanya);
        mrvKomentar = (RecyclerView) findViewById(R.id.rvKomentar);
        mEtKomentar = (TextInputEditText) findViewById(R.id.etKomentar);

        listKomen = new ArrayList<>();

        LinearLayoutManager llManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mrvKomentar.setLayoutManager(llManager);
        mAdapterKomen = new adapterKomen(listKomen);
        mrvKomentar.setAdapter(mAdapterKomen);

        loadIntent();

        mBtnKirim = (Button) findViewById(R.id.btnKirim);
        mBtnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtKomentar.getText().toString().isEmpty()) {
                    Toast.makeText(ActivityDetailTanya.this, "Harap isi komentar", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Insert atau push data komentar ke firebase
                FirebaseC.refPertanyaan.child(tanya.getKey_pertanyaan()).child("commentList").push()
                        .setValue(new mKomen(
                                FirebaseC.currentUser.getEmail().split("@")[0],
                                FirebaseC.currentUser.getEmail(),
                                mEtKomentar.getText().toString()
                        ));

                mEtKomentar.setText("");
            }
        });

    }

    mPertanyaan tanya;

    private void loadIntent() {
        if (getIntent().getExtras() != null) {
            tanya = (mPertanyaan) getIntent().getSerializableExtra("tanyaData");
            mNamaDetailTanya.setText(tanya.getNamaPertanyaan());
            mTitleDetailTanya.setText(tanya.getJudulPertanyaan());
            mDescDetailTanya.setText(tanya.getDeskripsiPertanyaan());
            loadComment();
        }

    }

    private void loadComment() {
        FirebaseC.refPertanyaan.child(tanya.getKey_pertanyaan()).child("commentList")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listKomen.clear();

                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                            mKomen komen = ds.getValue(mKomen.class);
                            listKomen.add(komen);
                            mAdapterKomen.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("", "Failed to read value.", databaseError.toException());
                    }
                });
    }

}
