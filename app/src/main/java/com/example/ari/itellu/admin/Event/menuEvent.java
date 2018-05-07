package com.example.ari.itellu.admin.Event;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class menuEvent extends Fragment {
    private static final String KEY_PARAM = "key_param";
    private ArrayList<mEvent> eventList;
    private adapterEvent ADAPTERevent;

    private RecyclerView rvEvent;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FloatingActionButton mFabEvent;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public menuEvent() {
        // Required empty public constructor
    }

    public static Bundle arguments(String param) {
        return new Bundler()
                .putString(KEY_PARAM, param)
                .get();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_event, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("event");
        rvEvent = view.findViewById(R.id.rvEvent);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshEvent);
        rvEvent.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mEvent eventItem = postSnapshot.getValue(mEvent.class);
                    eventList.add(eventItem);
                }
                ADAPTERevent = new adapterEvent(eventList, getActivity());
                rvEvent.setAdapter(ADAPTERevent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Bundle bundle = getArguments();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("refresh", "Refresh");
            }
        });

        mFabEvent = (FloatingActionButton) view.findViewById(R.id.fabEvent);
        mFabEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEvent inputEvent = new inputEvent();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, inputEvent);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return view;
    }

}
