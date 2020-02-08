package com.example.android.tourguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        int currentTab = sharedPref.getInt("PREFERENCE_CURRENT_TAB", defaultValue);
        Log.v("MainActivity.java", "currentTab: " + currentTab);

        ViewPager viewPager = findViewById(R.id.viewpager);

        TourGuideCategoriesPagerAdapter pageAdapter = new TourGuideCategoriesPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(currentTab);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
