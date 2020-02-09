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
public class FoodFragment extends Fragment {

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.category_list, container, false);

        /*
         * Create ist of cards for testing
         * */
        final ArrayList<GuideCard> foods = new ArrayList<>();
        foods.add(new GuideCard(R.string.food_indian_title, R.drawable.indiyskiy_street_2_small, R.string.food_indian_description,
                R.string.food_indian_address, R.string.food_indian_date, -1,
                "food", 13, 132,
                new int[]{R.drawable.indiyskiy_street_1_small, R.drawable.indiyskiy_street_2_small, R.drawable.indiyskiy_street_3_small},
                "http://7spicesstreetfood.ru/"));
        foods.add(new GuideCard(R.string.food_rynok_title, R.drawable.rinok_dolgoozerniy_1_small, R.string.food_rynok_description,
                R.string.food_rynok_address, R.string.food_rynok_date, -1,
                "food", 43, 22,
                new int[]{R.drawable.rinok_dolgoozerniy_1_small, R.drawable.rinok_dolgoozerniy_2_small, R.drawable.rinok_dolgoozerniy_3_small},
                "https://tkdoz.ru/"));
        foods.add(new GuideCard(R.string.food_pivo_title, R.drawable.pivovarnya_small, R.string.food_pivo_description,
                R.string.food_pivo_address, R.string.food_pivo_date, R.string.food_pivo_cost,
                "food", 14, 25,
                new int[]{R.drawable.pivovarnya_small},
                "https://vk.com/karlandfriedrich"));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        /*
         * On card click, user moves to Details activity
         **/
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                GuideCard card = foods.get(position);

                /*
                 * Before moving to Details activity, save current tab, so
                 * when user returned back to the same tab
                 **/
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PREFERENCE_CURRENT_TAB", 1);
                editor.apply();

                /*
                 * We are passing CardGuide object to be able to retrieve all information
                 * */
                Intent foodsIntent = new Intent(getActivity(), DetailsActivity.class);
                foodsIntent.putExtra("EXTRA_PLACE_OBJECT", card);
                startActivity(foodsIntent);
            }
        };

        /*
         * Setting the adapter to list cards
         * */
        RecyclerView.Adapter mAdapter = new CardAdapter(foods, listener);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
