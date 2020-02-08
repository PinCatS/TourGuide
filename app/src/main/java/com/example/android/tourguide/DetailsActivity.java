package com.example.android.tourguide;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private int MAX_TITLE_LENGTH = 18;

    private static ViewPager mPager;
    private ArrayList<Integer> imageModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GuideCard card = (GuideCard) getIntent().getSerializableExtra("EXTRA_PLACE_OBJECT");

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (card.isLiked()) {
                    card.dislike();
                    fab.setImageResource(R.drawable.heart_action_icon);
                    Toast.makeText(DetailsActivity.this, "Disliked!", Toast.LENGTH_SHORT).show();
                } else {
                    card.like();
                    fab.setImageResource(R.drawable.heart_action_icon_filled);
                    Toast.makeText(DetailsActivity.this, "Liked!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    String title = DetailsActivity.this.getString(card.getTitleResource());
                    if (title.length() > MAX_TITLE_LENGTH) {
                        title = title.substring(0, MAX_TITLE_LENGTH) + "...";
                    }
                    toolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    toolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList(card.getCardImageList());

        init();

        TextView textView = findViewById(R.id.details_title);
        textView.setText(this.getString(card.getTitleResource()));

        textView = findViewById(R.id.details_description);
        textView.setText(this.getString(card.getDescriptionResource()));

        textView = findViewById(R.id.date);
        if (card.getDate() != -1) {
            textView.setText(this.getString(card.getDate()));
        } else {
            textView.setVisibility(View.GONE);
        }

        textView = findViewById(R.id.address);
        if (card.getAddress() != -1) {
            textView.setText(this.getString(card.getAddress()));
        } else {
            textView.setVisibility(View.GONE);
        }

        textView = findViewById(R.id.cost);
        if (card.getCost() != -1) {
            textView.setText(this.getString(card.getCost()));
        } else {
            textView.setVisibility(View.GONE);
            textView = findViewById(R.id.cost_label);
            textView.setVisibility(View.GONE);
        }
    }

    private ArrayList<Integer> populateList(int[] cardImageList) {

        ArrayList<Integer> list = new ArrayList<>();

        for (Integer imageResourceId : cardImageList) {
            list.add(imageResourceId);
        }

        return list;
    }

    private void init() {

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(DetailsActivity.this, imageModelArrayList));

        CirclePageIndicator indicator = findViewById(R.id.indicator);

        if (imageModelArrayList.size() == 1) {
            indicator.setVisibility(View.GONE);
        } else {
            indicator.setViewPager(mPager);
            final float density = getResources().getDisplayMetrics().density;

            //Set circle indicator radius
            indicator.setRadius(5 * density);
        }
    }

}
