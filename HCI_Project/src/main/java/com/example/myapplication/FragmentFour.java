package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.LocalDate;
import java.util.ArrayList;


public class FragmentFour extends Fragment {

    BottomNavigationView bottom_nav_bar;
    ListView listView;
    TextView dayText;
    LocalDate recyclerViewSelectedDate;
    String updatedDateString = null;
    String updatedAssignmentNameString;
    double timePerSessionInput;
    long actualTimePerSession;
    ArrayList<Assignment> textFieldsRecycler;
    ListViewAdapter<Assignment> listViewAdapter;
    ArrayList<String> times;
    Button startSessionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_4_alternative, container, false);
        inflateWidgets(view);

       if(recyclerViewSelectedDate != null)
           dayText.setText(recyclerViewSelectedDate.toString());

       populateArrayList();

        listViewAdapter = new ListViewAdapter<Assignment>(
                getContext(), R.layout.layout_listview_2, textFieldsRecycler

        );
        listView.setAdapter(listViewAdapter);

        /**
         * setOnItemSelectedListener selected for Bottom Navigation bar
         * "back" takes you one fragment back (fragmentThree)
         * "start" takes you to timer
         * "settings" already chosen
         */

        bottom_nav_bar.setSelectedItemId(R.id.assignmentstab);
        bottom_nav_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hometab:
                        ((MainActivity) getActivity()).setViewPager(4);
                        return true;
                    case R.id.assignmentstab:
                        return true;
                    case R.id.settingstab:
                        ((MainActivity) getActivity()).setViewPager(2);
                        return true;
                }
                return false;
            }
        });

        startSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setViewPager(4);
            }
        });

        return view;
    }

    public void inflateWidgets(View view) {
        bottom_nav_bar = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
        listView = (ListView) view.findViewById(R.id.hourListView);
        dayText = (TextView) view.findViewById(R.id.dayText);
        textFieldsRecycler = new ArrayList<>();
        startSessionButton = view.findViewById(R.id.startSessionButton);
        times = new ArrayList<>();
        times.add("07:00");
        times.add("07:30");
        times.add("08:00");
        times.add("08:30");
        times.add("09:00");
        times.add("09:30");
        times.add("10:00");
        times.add("10:30");
        times.add("11:00");
        times.add("11:30");
        times.add("12:00");
        times.add("12:30");
        times.add("13:00");
        times.add("13:30");
        times.add("14:00");
        times.add("14:30");
        times.add("15:00");
        times.add("15:30");
        times.add("16:00");
        times.add("16:30");
        times.add("17:00");
        times.add("17:30");
        times.add("18:00");
        times.add("18:30");

    }

    public void update() {
        recyclerViewSelectedDate = LocalDate.parse(updatedDateString);
        dayText.setText(updatedDateString);
    }
    public void updateTwo() {
        actualTimePerSession = (long) timePerSessionInput / 1000;

    }
    public void updateThree() {

    }

    public void populateArrayList(){
        double time = timePerSessionInput / 10;
        int index = 0;
        for(int i = 0; i < time; i += 1800) {
            textFieldsRecycler.add(new Assignment(updatedAssignmentNameString, times.get(index)));
            index += 1;
        }
        for(int i = index; i < times.size(); i++) {
            textFieldsRecycler.add(new Assignment("", times.get(i)));
        }
    }
    /*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ((MainActivity) getActivity()).setViewPager(4);
    }

     */
}
