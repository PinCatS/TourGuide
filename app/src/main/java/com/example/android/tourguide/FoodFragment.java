package com.example.android.tourguide;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> foods = new ArrayList<>();
        foods.add(new GuideCard("Индийский стритфуд «7 Специй»", R.drawable.cake_image, getString(R.string.food_indian_description), "foods", 13, 132));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = foods.get(position);

                Intent foodsIntent = new Intent(getActivity(), DetailsActivity.class);
                foodsIntent.putExtra("EXTRA_PLACE_OBJECT", card);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 1);
                editor.apply();

                Log.v("FoodFragment.java", "saving position");

                startActivity(foodsIntent);
            }
        };

        mAdapter = new CardAdapter(foods, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
