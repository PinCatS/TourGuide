package com.example.android.tourguide;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TourGuidePagerAdapter extends FragmentPagerAdapter {

    private static final String[] CATEGORY_TITLES = {"Places", "Food", "Events", "Routes"};

    public TourGuidePagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return CATEGORY_TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlacesFragment();
        } else if (position == 1) {
            return new FoodFragment();
        } else if (position == 2) {
            return new EventsFragment();
        } else {
            return new RoutesFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CATEGORY_TITLES[position];
    }

}
