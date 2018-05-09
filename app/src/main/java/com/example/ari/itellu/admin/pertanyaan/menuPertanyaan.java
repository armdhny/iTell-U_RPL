package com.example.ari.itellu.admin.pertanyaan;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ari.itellu.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuPertanyaan extends Fragment {

    private FloatingActionButton mFab;

    public menuPertanyaan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_pertanyaan, container, false);
//        getActivity().setTitle("List Pertanyaan");

        FragmentStatePagerItemAdapter adapter = new FragmentStatePagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getContext())
                .add(FragmentPagerItem.of("All Question", listPertanyaan.class, listPertanyaan.arguments("new")))
                .add(FragmentPagerItem.of("My Question", listPertanyaan.class, listPertanyaan.arguments("myQuestion")))
                .create()
        );

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        mFab = (FloatingActionButton) view.findViewById(R.id.fabTanya);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPertanyaan inputPertanyaan = new inputPertanyaan();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, inputPertanyaan);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return view;
    }

}
