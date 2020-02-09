package com.example.android.tourguide;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class ExcursionFragment extends Fragment {

    public ExcursionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.category_list, container, false);

        /*
         * Create ist of cards for testing
         * */
        final ArrayList<GuideCard> excursions = new ArrayList<>();
        excursions.add(new GuideCard(R.string.excursion_top_title, R.drawable.top_2_small, R.string.excursion_top_description,
                -1, R.string.excursion_top_date, R.string.excursion_top_cost,
                "excursions", 13, 132,
                new int[]{R.drawable.top_2_small, R.drawable.top_1_small, R.drawable.top_3_small, R.drawable.top_4_small},
                "https://experience.tripster.ru/experience/18012/?utm_source=kudago&utm_medium=cpc&utm_campaign=saint-petersburg&utm_content=18012"));
        excursions.add(new GuideCard(R.string.excursion_secret_title, R.drawable.secret_2_small, R.string.excursion_secret_description,
                -1, R.string.excursion_secret_date, R.string.excursion_secret_cost,
                "excursions", 43, 22,
                new int[]{R.drawable.secret_2_small, R.drawable.secret_1_small, R.drawable.secret_3_small},
                "https://experience.tripster.ru/experience/16809/?utm_source=kudago&utm_medium=cpc&utm_campaign=saint-petersburg&utm_content=16809"));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        /*
         * On card click, user moves to Details activity
         * */
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = excursions.get(position);

                /*
                 * Before moving to Details activity, save current tab, so
                 * when user returned back to the same tab
                 * */
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 3);
                editor.apply();

                /*
                 * We are passing CardGuide object to be able to retrieve all information
                 * */
                Intent routesIntent = new Intent(getActivity(), DetailsActivity.class);
                routesIntent.putExtra("EXTRA_PLACE_OBJECT", card);
                startActivity(routesIntent);
            }
        };

        /*
         * Setting the adapter to list cards
         * */
        RecyclerView.Adapter mAdapter = new CardAdapter(excursions, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
