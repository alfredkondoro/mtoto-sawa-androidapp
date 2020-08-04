package com.ab.mtotosawa.controller;

import com.ab.mtotosawa.views.DonateFragment;
import com.ab.mtotosawa.views.EventFragment;
import com.ab.mtotosawa.views.HomeFragment;
import com.ab.mtotosawa.views.MapsFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DevPagerAdapter extends FragmentPagerAdapter {
    public DevPagerAdapter(FragmentManager fm) {
        super (fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment ();
            case 1:
                return new DonateFragment ();
            case 2:
                return new MapsFragment ();
            case 3:
                return new EventFragment ();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0: return "HOME";
            case 1: return "DONATE";
            case 2: return "MAPS";
            case 3: return "EVENT";
        }
        return null;
    }
}
