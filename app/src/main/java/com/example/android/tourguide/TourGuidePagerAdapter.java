package com.example.android.tourguide;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TourGuidePagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_ITEMS = 2;
    private static final String[] CATEGORY_TITLES = {"Places", "Food"};

    public TourGuidePagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlacesFragment();
        } else {
            return new FoodFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CATEGORY_TITLES[position];
    }

}
