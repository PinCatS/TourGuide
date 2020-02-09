package com.example.android.tourguide;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/*
 * View pager adapter for different card categories
 * */
public class TourGuideCategoriesPagerAdapter extends FragmentPagerAdapter {

    private String[] mCategoryLabels;

    TourGuideCategoriesPagerAdapter(FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mCategoryLabels = new String[]{context.getString(R.string.card_category_places),
                context.getString(R.string.card_category_food),
                context.getString(R.string.card_category_events),
                context.getString(R.string.card_category_excursions),};
    }

    @Override
    public int getCount() {
        return mCategoryLabels.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlacesFragment();
        } else if (position == 1) {
            return new FoodFragment();
        } else if (position == 2) {
            return new EventsFragment();
        } else {
            return new ExcursionFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryLabels[position];
    }

}
