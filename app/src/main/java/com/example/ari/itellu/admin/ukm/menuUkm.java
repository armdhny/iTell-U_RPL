package com.example.ari.itellu.admin.ukm;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuUkm extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String KEY_PARAM = "key_param";
    private ArrayList<mUkm> ukmList;
    private ukmAdapter UKMadapter;

    private RecyclerView rvFoto;
    private SwipeRefreshLayout swipeRefresh;
    private FloatingActionButton fabUkm;


    public menuUkm() {
        // Required empty public constructor
    }
    public static Bundle arguments(String param){
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
        View view = inflater.inflate(R.layout.fragment_menu_ukm, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ukm");
        rvFoto = view.findViewById(R.id.rvFoto);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        rvFoto.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ukmList = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    mUkm ukmItem= postSnapshot.getValue(mUkm.class);
                    ukmList.add(ukmItem);
                }
                UKMadapter = new ukmAdapter(ukmList, getActivity());
                rvFoto.setAdapter(UKMadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Bundle bundle = getArguments();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                loadData();
            }
        });

        fabUkm = (FloatingActionButton) view.findViewById(R.id.fabUKM);
        if (FirebaseC.mAuth.getCurrentUser().getEmail().equals("admin@admin.com")) {
            fabUkm.setVisibility(view.VISIBLE);
        } else {
            fabUkm.setVisibility(view.GONE);

        }
        fabUkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputUkm inputUkm = new inputUkm();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction =fragmentManager.beginTransaction();
                transaction.replace(R.id.container1,inputUkm);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return view;


    }


//    private void loadData() {
//        if ()
//    }

}
