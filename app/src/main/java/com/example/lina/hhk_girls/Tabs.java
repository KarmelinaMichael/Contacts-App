package com.example.lina.hhk_girls;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Tabs extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Tabs(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Ebtda2y tab1 = new Ebtda2y();
                return tab1;
            case 1:
                E3dady tab2 = new E3dady();
                return tab2;
            case 2:
                Thanawy tab3 = new Thanawy();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}