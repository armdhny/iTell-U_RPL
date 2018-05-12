package com.example.ari.itellu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ari.itellu.admin.Event.menuEvent;
import com.example.ari.itellu.admin.Informasi.menuInformasi;
import com.example.ari.itellu.admin.komunitas.menuKomunitas;
import com.example.ari.itellu.admin.ukm.menuUkm;

/**
 * Created by Tavs on 12/05/2018.
 */

public class MenuHomeAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public MenuHomeAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new menuInformasi();
            case 1:
                return new menuUkm();
            case 2:
                return new menuKomunitas();
            case 3:
                return new menuEvent();
//            case 4:
//                return new menuPertanyaan();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
