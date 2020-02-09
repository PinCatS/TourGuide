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
public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.category_list, container, false);

        /*
         * Create ist of cards for testing
         * */
        final ArrayList<GuideCard> events = new ArrayList<>();
        events.add(new GuideCard(R.string.events_cheese_title, R.drawable.craft_cheese_1_small, R.string.events_cheese_description,
                R.string.events_cheese_address, R.string.events_cheese_date, -1,
                "events", 13, 132,
                new int[]{R.drawable.craft_cheese_1_small, R.drawable.craft_cheese_2_small, R.drawable.craft_cheese_3_small},
                "https://tkdoz.ru/"));
        events.add(new GuideCard(R.string.events_balley_title, R.drawable.balley_1_small, R.string.events_balley_description,
                R.string.events_balley_address, -1, R.string.events_balley_cost,
                "events", 43, 22,
                new int[]{R.drawable.balley_1_small, R.drawable.balley_2_small, R.drawable.balley_3_small},
                "https://mariinskii.com/events/jaroslavna_zatmenie/?utm_source=google&utm_medium=cpc&utm_term=%2B%D0%BC%D0%B0%D1%80%D0%B8%D0%B8%D0%BD%D1%81%D0%BA%D0%B8%D0%B9%20%2B%D1%8F%D1%80%D0%BE%D1%81%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%20%2B%D0%B7%D0%B0%D1%82%D0%BC%D0%B5%D0%BD%D0%B8%D0%B5&utm_content=405938492787&utm_campaign=K50_8559434100&utm_campaign_id=8559434100&utm_group_id=89177045240&utm_term_id=kwd-552230022702"));


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
                GuideCard card = events.get(position);

                /*
                 * Before moving to Details activity, save current tab, so
                 * when user returned back to the same tab
                 * */
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 2);
                editor.apply();

                /*
                 * We are passing CardGuide object to be able to retrieve all information
                 * */
                Intent eventsIntent = new Intent(getActivity(), DetailsActivity.class);
                eventsIntent.putExtra("EXTRA_PLACE_OBJECT", card);
                startActivity(eventsIntent);
            }
        };

        /*
         * Setting the adapter to list cards
         * */
        RecyclerView.Adapter mAdapter = new CardAdapter(events, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
