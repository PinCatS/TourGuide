package com.example.android.tourguide;

import java.io.Serializable;

public class GuideCard implements Serializable {
    private String mTitle;
    private int mImageResourceId;
    private float mRate = 0;
    private int mNumberOfReviews = 0;
    private String mDescription;
    private String mCategory;

    private final float MAX_RATE = 5.0f;

    GuideCard(String title, int imageResourceId, String description, String category) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
        mCategory = category;
    }

    GuideCard(String title, int imageResourceId, String description, String category, float rate, int numberOfReview) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
        mNumberOfReviews = numberOfReview;
        mRate = rate > MAX_RATE ? MAX_RATE : rate;
        mCategory = category;
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

    public String getCategory() {
        return mCategory;
    }
}
