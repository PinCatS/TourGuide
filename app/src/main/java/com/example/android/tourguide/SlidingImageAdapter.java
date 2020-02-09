package com.example.android.tourguide;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

/*
 * View pager adapter for sliding images in the DetailsActivity
 * */
public class SlidingImageAdapter extends PagerAdapter {

    private ArrayList<Integer> mImageModelArrayList;
    private LayoutInflater mInflater;

    SlidingImageAdapter(Context context, ArrayList<Integer> imageModelArrayList) {
        mImageModelArrayList = imageModelArrayList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageModelArrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = mInflater.inflate(R.layout.sliding_image_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.sliding_image);


        imageView.setImageResource(mImageModelArrayList.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
