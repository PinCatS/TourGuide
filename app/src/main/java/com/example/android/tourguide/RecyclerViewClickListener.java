package com.example.android.tourguide;

import android.view.View;

/*
 * Interface to make RecyclerView items clickable
 * */
public interface RecyclerViewClickListener {
    void onClick(View view, int position);
}
