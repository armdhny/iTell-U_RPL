package com.example.ari.itellu.admin.pertanyaan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class listPertanyaan extends Fragment {

    private static final String KEY_PARAM = "key_param";
    private ArrayList<mPertanyaan> tanyaList;
    private adapterPertanyaan mAdapter;
    private RecyclerView rvTanya;
    private SwipeRefreshLayout swipeRefresh;


    public listPertanyaan() {
        // Required empty public constructor
    }


    public static Bundle arguments(String param) {
        return new Bundler()
                .putString(KEY_PARAM, param)
                .get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pertanyaan, container, false);

        rvTanya = view.findViewById(R.id.rvTanya);
        swipeRefresh = view.findViewById(R.id.swipeRefreshTanya);
        Bundle bundle = getArguments();

        menu = bundle.getString(KEY_PARAM);

        tanyaList = new ArrayList<>();

        rvTanya.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new adapterPertanyaan(tanyaList, getActivity());
        rvTanya.setAdapter(mAdapter);
        loadData();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        return view;
    }

    int commentCount = 0;

    private void loadData() {
        if (menu.equals("new")) {
            swipeRefresh.setRefreshing(true);
            FirebaseC.refPertanyaan.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tanyaList.clear();
                    commentCount = 0;

                    for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                        mPertanyaan tanya = ds.getValue(mPertanyaan.class);
                        tanyaList.add(tanya);
                        mAdapter.notifyDataSetChanged();
                    }
                    swipeRefresh.setRefreshing(false);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("", "Failed to read value.", error.toException());
                }
            });
        } else {
            swipeRefresh.setRefreshing(true);
            FirebaseC.refPertanyaan.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tanyaList.clear();

                    for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                        mPertanyaan tanya = ds.getValue(mPertanyaan.class);

                        if (tanya.getEmail_asker().equals(FirebaseC.currentUser.getEmail())) {
                            tanyaList.add(tanya);
                            mAdapter.notifyDataSetChanged();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("", "Failed to read value.", databaseError.toException());
                }
            });
        }
    }


}
