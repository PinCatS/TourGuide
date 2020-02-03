package com.example.android.tourguide;

public class GuideCard {
    private String mTitle;
    private int mImageResourceId;
    private float mRate = 0;
    private int mNumberOfReviews = 0;
    private String mDescription;

    private final float MAX_RATE = 5.0f;

    GuideCard(String title, int imageResourceId, String description) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
    }

    GuideCard(String title, int imageResourceId, String description, float rate, int numberOfReview) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
        mNumberOfReviews = numberOfReview;
        mRate = rate > MAX_RATE ? MAX_RATE : rate;
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
