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
public class PlacesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> places = new ArrayList<>();
        places.add(new GuideCard(R.string.places_oleni_title, R.drawable.oleni_1_small, R.string.places_oleni_description,
                R.string.places_oleni_address, -1, -1,
                "places", 13, 132,
                new int[]{R.drawable.oleni_1_small, R.drawable.oleni_2_small, R.drawable.oleni_3_small}));
        places.add(new GuideCard(R.string.places_planet_title, R.drawable.planetariy_1_small, R.string.places_planet_description,
                R.string.places_planet_address, R.string.places_planet_date, -1,
                "places", 43, 22,
                new int[]{R.drawable.planetariy_1_small, R.drawable.planetariy_2_small, R.drawable.planetariy_3_small}));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = places.get(position);
                Intent placeIntent = new Intent(getActivity(), DetailsActivity.class);
                placeIntent.putExtra("EXTRA_PLACE_OBJECT", card);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 0);
                editor.apply();

                Log.v("PlacesFragment.java", "saving position");

                startActivity(placeIntent);
            }
        };

        mAdapter = new CardAdapter(places, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
