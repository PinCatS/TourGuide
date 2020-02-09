package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private int MAX_TITLE_LENGTH = 18;
    private ArrayList<Integer> imageModelArrayList;
    private GuideCard card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get passed GuideCard object
        card = (GuideCard) getIntent().getSerializableExtra("EXTRA_PLACE_OBJECT");

        /*
         * Setup FAB button functionality.
         * When user pushes the button, like is increased.
         * If user presses it again,  like is taken back.
         *
         * Also toast messages are displayed during pushes.
         * Like icon changes depending on liked/not-liked states
         * */
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (card.isLiked()) {
                    card.dislike();
                    fab.setImageResource(R.drawable.heart_action_icon);
                    Toast.makeText(DetailsActivity.this, getString(R.string.dislike_action), Toast.LENGTH_SHORT).show();
                } else {
                    card.like();
                    fab.setImageResource(R.drawable.heart_action_icon_filled);
                    Toast.makeText(DetailsActivity.this, getString(R.string.like_action), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
         * That piece of code handles the visibility of the toolbar title.
         * Title appears only when toolbar becomes pinned.
         *
         * When the screen is scrolled up, verticalOffset is being decreased till scrollRange.
         * */
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
                    // trim the toolbar title in case it doesn't fit
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

        initSlider();
        fillLayoutWithCardDetails(card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_activity_details, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch reference to the share action provider
        TourGuideActionProvider tourGuideShareActionProvider =
                (TourGuideActionProvider) MenuItemCompat.getActionProvider(item);

        tourGuideShareActionProvider.setListTitle(DetailsActivity.this.getString(R.string.dialog_share_label));
        ArrayList<String> shareOptions = new ArrayList<>();
        shareOptions.add(DetailsActivity.this.getString(R.string.dialog_share_option_share));
        tourGuideShareActionProvider.setShareOptionList(shareOptions);

        tourGuideShareActionProvider.setShareIntent(
                Intent.createChooser(prepareShareIntent(card.getLinkUrl()),
                        DetailsActivity.this.getString(R.string.share_provider_all_title)));
        return true;
    }

    public Intent prepareShareIntent(String url) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        sendIntent.setType("text/plain");

        return sendIntent;
    }

    private ArrayList<Integer> populateList(int[] cardImageList) {

        ArrayList<Integer> list = new ArrayList<>();

        if (cardImageList != null) {
            for (Integer imageResourceId : cardImageList) {
                list.add(imageResourceId);
            }
        }

        return list;
    }

    /*
     * Initializes image slider
     *
     * Basically it sets up view pager with an adapter and sets circle indicators
     * if there is only one image, it removes indicator layout
     * */
    private void initSlider() {

        ViewPager mPager = findViewById(R.id.pager);
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

    /*
     * Fills details layout with cards data
     *
     * Some data can be absent. In that case the view which corresponds to the data is removed
     * from the layout.
     *
     * @param card a GuideCard object which contains all information about the tour card
     * */
    private void fillLayoutWithCardDetails(GuideCard card) {
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
}
