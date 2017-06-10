package com.brahmakumari.powerofmind.adapter;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.brahmakumari.powerofmind.ui.fragment.EventsFragment;
import com.brahmakumari.powerofmind.ui.fragment.NewsFragment;

/**
 * Created by ishitabhandari on 08/06/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    int numoftabs;
    public ViewPagerAdapter(FragmentManager fm, int numoftabs) {
        super(fm);
        this.numoftabs= numoftabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewsFragment tab1 = new NewsFragment();
                return tab1;
            case 1:
                EventsFragment tab2 = new EventsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
