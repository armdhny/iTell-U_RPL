package com.example.ari.itellu.admin.pertanyaan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ari.itellu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuPertanyaan extends Fragment {


    public menuPertanyaan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_pertanyaan, container, false);
    }

}
