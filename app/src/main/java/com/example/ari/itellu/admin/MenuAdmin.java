package com.example.ari.itellu.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ari.itellu.R;
import com.example.ari.itellu.admin.Informasi.menuInformasi;
import com.example.ari.itellu.admin.komunitas.menuKomunitas;
import com.example.ari.itellu.admin.Event.menuEvent;
import com.example.ari.itellu.admin.pertanyaan.menuPertanyaan;
import com.example.ari.itellu.admin.ukm.menuUkm;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuAdmin extends Fragment {
    private Button mCrudTelyu, mCrudUkm, mCrudKomuniti, mCrudEvent, mPertanyaan;

    public MenuAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmenView = inflater.inflate(R.layout.fragment_menu_admin, container, false);

        //Inisiasi button
        mCrudTelyu = fragmenView.findViewById(R.id.menu_telyu);
        mCrudTelyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Crud TelYu", "Menuju Menu Crud telyu");

                Fragment info_telyu = new menuInformasi();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, info_telyu);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        mCrudUkm = fragmenView.findViewById(R.id.menu_ukm);
        mCrudUkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Crud UKM", "Menuju Menu Crud UKM");
                Fragment UKM = new menuUkm();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction =fragmentManager.beginTransaction();
                transaction.replace(R.id.container1,UKM);
                transaction.addToBackStack(null);

                transaction.commit();


            }
        });

        mCrudKomuniti = fragmenView.findViewById(R.id.menu_komunitas);
        mCrudKomuniti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Crud TelYu", "Menuju Menu crud Komuniti");
                Fragment komuniti = new menuKomunitas();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, komuniti);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        mCrudEvent = fragmenView.findViewById(R.id.menu_event);
        mCrudEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Crud TelYu", "Menuju Menu crud Event");
                Fragment event = new menuEvent();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, event);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        mPertanyaan = fragmenView.findViewById(R.id.menu_pertanyaan);
        mPertanyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mPertanyaan", "Menu Pertanyaan");
                Fragment tanya = new menuPertanyaan();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container1, tanya);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return fragmenView;
    }


}
