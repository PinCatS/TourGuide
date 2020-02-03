package com.example.android.tourguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        GuideCard card = (GuideCard) getIntent().getSerializableExtra("EXTRA_PLACE_OBJECT");

        this.setTitle(card.getTitle());
    }
}
