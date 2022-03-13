package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class FragmentOne extends Fragment{

    Button addAssignmentBtn;
    BottomNavigationView bottom_nav_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        inflateWidgets(view); //Inflates all the widgets for the current fragment

        /**
         * setOnItemSelectedListener for Bottom Navigation bar
         *
         * "start" takes you to timer           (fragmentTwo)
         * "settings" takes you to assignments (fragmentThree)
         */

        bottom_nav_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.hometab:
                            ((MainActivity)getActivity()).setViewPager(4);
                            return true;
                        case R.id.assignmentstab:
                            ((MainActivity)getActivity()).setViewPager(3);
                            return true;
                    }
                    return false;
            }
        });

        /**
         * setOnclickListener for the "NEW ASSIGNMENT" button
         * takes you to fragmentTwo
         */

        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        return view;
    }


    public void inflateWidgets(View view) {
        addAssignmentBtn = (Button) view.findViewById(R.id.addAssignmentButton);

        bottom_nav_bar = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
    }

}
