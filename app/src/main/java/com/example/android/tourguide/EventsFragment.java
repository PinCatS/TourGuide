package com.example.android.tourguide;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class EventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public EventsFragment() {
        // Required empty public constructor
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = v.findViewById(R.id.card_title);
            Log.v("EventsFragment.java", "OnClickListener: " + textView.getText());
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<GuideCard> events = new ArrayList<>();
        events.add(new GuideCard(R.string.events_cheese_title, R.drawable.craft_cheese_1_small, R.string.events_cheese_description,
                R.string.events_cheese_address, R.string.events_cheese_date, -1,
                "events", 13, 132,
                new int[]{R.drawable.craft_cheese_1_small, R.drawable.craft_cheese_2_small, R.drawable.craft_cheese_3_small}));
        events.add(new GuideCard(R.string.events_balley_title, R.drawable.balley_1_small, R.string.events_balley_description,
                R.string.events_balley_address, -1, R.string.events_balley_cost,
                "events", 43, 22,
                new int[]{R.drawable.balley_1_small, R.drawable.balley_2_small, R.drawable.balley_3_small}));

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = events.get(position);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 2);
                editor.apply();

                Log.v("FoodFragment.java", "saving position");

                Intent eventsIntent = new Intent(getActivity(), DetailsActivity.class);
                eventsIntent.putExtra("EXTRA_PLACE_OBJECT", card);
                startActivity(eventsIntent);
            }
        };

        mAdapter = new CardAdapter(events, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
