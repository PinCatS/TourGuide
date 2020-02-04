package com.example.android.tourguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.FoodViewHolder> {

    ArrayList<GuideCard> cards;
    RecyclerViewClickListener mListener;

    CardAdapter(ArrayList<GuideCard> cards, RecyclerViewClickListener listener) {
        this.cards = cards;
        this.mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CardAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new FoodViewHolder(v, mListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        GuideCard card = cards.get(position);
        holder.cardTitle.setText(card.getTitle());
        holder.cardImage.setImageResource(card.getImageResourceId());
        holder.cardLikes.setText(Integer.toString(card.getLikes()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardTitle;
        ImageView cardImage;
        TextView cardLikes;
        RecyclerViewClickListener mListener;

        FoodViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            cardTitle = v.findViewById(R.id.card_title);
            cardImage = v.findViewById(R.id.card_image);
            cardLikes = v.findViewById(R.id.likes_count);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }

}
