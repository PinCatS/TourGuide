package com.example.android.tourguide;

import java.io.Serializable;

public class GuideCard implements Serializable {
    private String mTitle;
    private int mImageResourceId;
    private int mLikes = 0;
    private int mNumberOfReviews = 0;
    private String mDescription;
    private String mCategory;

    GuideCard(String title, int imageResourceId, String description, String category) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
        mCategory = category;
    }

    GuideCard(String title, int imageResourceId, String description, String category, int likes, int numberOfReview) {
        mTitle = title;
        mImageResourceId = imageResourceId;
        mDescription = description;
        mNumberOfReviews = numberOfReview;
        mLikes = likes;
        mCategory = category;
    }

    String getTitle() {
        return mTitle;
    }

    int getImageResourceId() {
        return mImageResourceId;
    }

    int getLikes() {
        return mLikes;
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
