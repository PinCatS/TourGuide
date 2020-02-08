package com.example.android.tourguide;

import java.io.Serializable;

public class GuideCard implements Serializable {
    private int mTitleResourceId;
    private int mImageResourceId;
    private int mLikes = 0;
    private int mNumberOfReviews = 0;
    private int mDescriptionResourceId;
    private String mCategory;
    private boolean mIsLiked = false;
    private int[] mCardImageList;
    private int mAddressResource;
    private int mDateResource;
    private int mCostResource;

    GuideCard(int titleResourceId, int imageResourceId, int descriptionResourceId,
              int addressResource, int dateResource, int costResource,
              String category,
              int likes, int numberOfReview,
              int[] cardImageList) {
        mTitleResourceId = titleResourceId;
        mImageResourceId = imageResourceId;
        mDescriptionResourceId = descriptionResourceId;
        mNumberOfReviews = numberOfReview;
        mLikes = likes;
        mCategory = category;
        mCardImageList = cardImageList;
        mAddressResource = addressResource;
        mDateResource = dateResource;
        mCostResource = costResource;
    }

    int getTitleResource() {
        return mTitleResourceId;
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

    int getDescriptionResource() {
        return mDescriptionResourceId;
    }

    String getCategory() {
        return mCategory;
    }

    public int[] getCardImageList() {
        return mCardImageList;
    }

    public int getAddress() {
        return mAddressResource;
    }

    public int getDate() {
        return mDateResource;
    }

    public int getCost() {
        return mCostResource;
    }

    void like() {
        mLikes++;
        mIsLiked = true;
    }

    void dislike() {
        mLikes--;
        mIsLiked = false;
    }

    boolean isLiked() {
        return mIsLiked;
    }

}
