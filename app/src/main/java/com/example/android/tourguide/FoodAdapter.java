package com.example.android.tourguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    ArrayList<String> foods;

    public FoodAdapter(ArrayList<String> foods) {
        this.foods = foods;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);

        FoodViewHolder vh = new FoodViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardTitle.setText(foods.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView cardTitle;

        public FoodViewHolder(View v) {
            super(v);
            cardTitle = v.findViewById(R.id.card_text);
        }
    }

}
