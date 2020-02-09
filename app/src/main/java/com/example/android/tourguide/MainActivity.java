package com.example.android.tourguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Restore current tab position when user open the app
         * or returns back from the Details activity.
         * If user opens the app first time default tab is the first one (position 0)
         *
         * Possibly there is a better solution.
         * */
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        int currentTab = sharedPref.getInt("PREFERENCE_CURRENT_TAB", defaultValue);

        /*
         * Use View Pager and connect it with the TabLayout.
         * */
        ViewPager viewPager = findViewById(R.id.viewpager);

        TourGuideCategoriesPagerAdapter pageAdapter = new TourGuideCategoriesPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(currentTab);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
