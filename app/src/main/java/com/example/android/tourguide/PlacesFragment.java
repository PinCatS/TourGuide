package com.example.android.tourguide;


import android.os.Bundle;
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

        ArrayList<GuideCard> places = new ArrayList<>();
        places.add(new GuideCard("C.A.K.E", R.drawable.cake_image, getString(R.string.cake_confectionary_description)));
        places.add(new GuideCard("Ресторан «Терраса»", R.drawable.terrasa, getString(R.string.terrasa_restaurant_description)));
        places.add(new GuideCard("Rыба", R.drawable.ryba, getString(R.string.ryba_restaurant_description)));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CardAdapter(places);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
