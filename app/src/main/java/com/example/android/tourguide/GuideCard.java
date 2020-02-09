package com.example.android.tourguide;

import java.io.Serializable;

class GuideCard implements Serializable {
    private int mTitleResourceId;
    private int mImageResourceId;
    private int mLikes;
    private int mNumberOfReviews;
    private int mDescriptionResourceId;
    private String mCategory;
    private boolean mIsLiked;
    private int[] mCardImageList;
    private int mAddressResource;
    private int mDateResource;
    private int mCostResource;
    private String mLinkUrl;

    GuideCard(int titleResourceId, int imageResourceId, int descriptionResourceId,
              int addressResource, int dateResource, int costResource,
              String category,
              int likes, int numberOfReview,
              int[] cardImageList, String linkUrl) {
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
        mLinkUrl = linkUrl;
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

    int[] getCardImageList() {
        return mCardImageList;
    }

    int getAddress() {
        return mAddressResource;
    }

    int getDate() {
        return mDateResource;
    }

    int getCost() {
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

    public String getLinkUrl() {
        return mLinkUrl;
    }
}
