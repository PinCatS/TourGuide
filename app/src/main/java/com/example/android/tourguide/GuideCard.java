package com.example.android.tourguide;

public class GuideCard {
    private String mTitle;
    private int mImageResourceId;
    private float mRate = 0;
    private int mNumberOfReviews = 0;
    private String mDescription;

    GuideCard(String title, int imageResourceId, String description) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
    }

    String getTitle() {
        return mTitle;
    }

    int getImageResourceId() {
        return mImageResourceId;
    }

    float getRate() {
        return mRate;
    }

    int getNumberOfReviews() {
        return mNumberOfReviews;
    }

    String getDescription() {
        return mDescription;
    }
}
