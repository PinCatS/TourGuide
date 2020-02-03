package com.example.android.tourguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.FoodViewHolder> {

    ArrayList<GuideCard> cards;

    CardAdapter(ArrayList<GuideCard> cards) {
        this.cards = cards;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CardAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new FoodViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        GuideCard card = cards.get(position);
        holder.cardTitle.setText(card.getTitle());
        holder.cardDescription.setText(card.getDescription());
        holder.cardImage.setImageResource(card.getImageResourceId());
        holder.cardRateValue.setText(String.format(Locale.ENGLISH, "%.1f", card.getRate()));
        holder.cardReviewsCount.setText(holder.cardReviewsCount.getContext().getString(R.string.reviews_count_string, card.getNumberOfReviews()));
        holder.indicatorRatingBar.setRating(card.getRate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        TextView cardDescription;
        ImageView cardImage;
        TextView cardRateValue;
        TextView cardReviewsCount;
        RatingBar indicatorRatingBar;

        FoodViewHolder(View v) {
            super(v);
            cardTitle = v.findViewById(R.id.card_title);
            cardDescription = v.findViewById(R.id.card_description);
            cardImage = v.findViewById(R.id.card_image);
            cardRateValue = v.findViewById(R.id.card_rate_value);
            cardReviewsCount = v.findViewById(R.id.card_comments_count);
            indicatorRatingBar = v.findViewById(R.id.ratingBar_indicator);
        }
    }

}
