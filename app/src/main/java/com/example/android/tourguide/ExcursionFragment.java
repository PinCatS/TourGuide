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
public class ExcursionFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ExcursionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> routes = new ArrayList<>();
        routes.add(new GuideCard("Экскурсия «Петербург: вид сверху»", R.drawable.cake_image, getString(R.string.excursion_roof_description), "routes", 11, 4));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = routes.get(position);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 3);
                editor.apply();

                Log.v("FoodFragment.java", "saving position");

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
