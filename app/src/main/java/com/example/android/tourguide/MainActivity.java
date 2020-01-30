package com.example.android.tourguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);

        TourGuidePagerAdapter pageAdapter = new TourGuidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
    }

}
