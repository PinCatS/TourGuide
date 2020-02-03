package com.example.android.tourguide;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = v.findViewById(R.id.card_title);
            Log.v("FoodFragment.java", "OnClickListener: " + textView.getText());
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> foods = new ArrayList<>();
        foods.add(new GuideCard("C.A.K.E", R.drawable.cake_image, getString(R.string.cake_confectionary_description)));
        foods.add(new GuideCard("Ресторан «Терраса»", R.drawable.terrasa, getString(R.string.terrasa_restaurant_description)));
        foods.add(new GuideCard("Rыба", R.drawable.ryba, getString(R.string.ryba_restaurant_description)));

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
                startActivity(foodsIntent);
            }
        };

        mAdapter = new CardAdapter(foods, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
