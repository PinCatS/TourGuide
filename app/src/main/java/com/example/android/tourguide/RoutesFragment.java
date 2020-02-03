package com.example.android.tourguide;


import android.content.Intent;
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
public class RoutesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public RoutesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> routes = new ArrayList<>();
        routes.add(new GuideCard("C.A.K.E", R.drawable.cake_image, getString(R.string.cake_confectionary_description), "routes"));
        routes.add(new GuideCard("Ресторан «Терраса»", R.drawable.terrasa, getString(R.string.terrasa_restaurant_description), "routes"));
        routes.add(new GuideCard("Rыба", R.drawable.ryba, getString(R.string.ryba_restaurant_description), "routes"));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = routes.get(position);

                Intent routesIntent = new Intent(getActivity(), DetailsActivity.class);
                routesIntent.putExtra("EXTRA_PLACE_OBJECT", card);
                startActivity(routesIntent);
            }
        };

        mAdapter = new CardAdapter(routes, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
