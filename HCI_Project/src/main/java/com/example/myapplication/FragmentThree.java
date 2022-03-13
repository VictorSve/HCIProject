package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class FragmentThree extends Fragment {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    BottomNavigationView bottom_nav_bar;
    Button forwardButton;
    Button backwardButton;
    LocalDate localDate;
    LocalDate assignmentDueDate;
    String dueDate;
    ArrayList<LocalDate> days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_3, container, false);

        inflateWidgets(view); //Inflates all the widgets for the current fragment


        /**
         * Creates a new calendarAdapter of class CalendarAdapter
         * We create a GridLayoutManager with 7 "grids" to represent
         * a week and set the adapter, layoutManager to our RecyclerView
         */
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, new CalendarAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position, LocalDate date) {
                localDate = date;
                String output = date.toString();

                ((MainActivity) requireActivity()).updateRecyclerSelectedDate(output);
                ((MainActivity)getActivity()).setViewPager(3);
            }

        });


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);


        /**
         * SetOnClickListener for "<-" button, which uses the RecyclerView
         * to set the grid to be the previous week
         */

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDate = localDate.minusWeeks(1);
                monthYearText.setText(monthYearFromDate(localDate));
                ArrayList<LocalDate> daysInWeekArray = daysInWeekArray(localDate);
                RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getContext(), 7);
                CalendarAdapter calendarAdapter2 = new CalendarAdapter(daysInWeekArray, new CalendarAdapter.OnItemListener() {
                    @Override
                    public void onItemClick(int position, LocalDate date) {
                        String output = date.toString();
                        ((MainActivity) requireActivity()).updateRecyclerSelectedDate(output);
                        localDate = date.minusWeeks(1);
                        ((MainActivity)getActivity()).setViewPager(3);
                    }
                });


                calendarRecyclerView.setLayoutManager(layoutManager2);
                calendarRecyclerView.setAdapter(calendarAdapter2);

            }
        });

        /**
         * SetOnClickListener for "->" button, which uses the RecyclerView
         * to set the grid to be the next week
         */

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                localDate = localDate.plusWeeks(1);
                monthYearText.setText(monthYearFromDate(localDate));
                ArrayList<LocalDate> daysInWeekArray = daysInWeekArray(localDate);
                RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getContext(), 7);
                CalendarAdapter calendarAdapter1 = new CalendarAdapter(daysInWeekArray, new CalendarAdapter.OnItemListener() {
                    @Override
                    public void onItemClick(int position, LocalDate date) {
                        String output = date.toString();
                        ((MainActivity) requireActivity()).updateRecyclerSelectedDate(output);
                        localDate = date.plusWeeks(1);
                        ((MainActivity)getActivity()).setViewPager(3);
                    }
                });
                calendarRecyclerView.setLayoutManager(layoutManager1);
                calendarRecyclerView.setAdapter(calendarAdapter1);
            }
        });

        /**
         * setOnItemSelectedListener for Bottom Navigation bar
         * "assignments" takes you to the assignments tab (fragmentThree) (current)
         * "start" takes you to timer           (fragmentFour)
         * "settings" takes you to assignments (fragmentTwo)
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
                    case R.id.settingstab:
                        ((MainActivity)getActivity()).setViewPager(1);
                        return true;

                }
                return false;
            }
        });
        return view;
    }

    /**
     *
     * @param date - A local date
     * @return a date formated as "MMMM yyyy" i.e 22March 2022
     */
    public static String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    /**
     *
     * @param selectedDate - for a selected date
     * @return the weekdays of the coming week as 3 - 4 - 5 - 6 - 7 ...
     */

    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        while (current.isBefore(endDate)) {
            days.add(current);
            current = current.plusDays(1);
        }
        return days;
    }


    private static LocalDate sundayForDate(LocalDate current) {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo)) {
            if (current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }

        return null;
    }

    /**
     * update() takes the String sent by FragmentTwo
     * and formats it to a LocalDate and places
     * the value in the assignmentDue variable
     */
    public void update() {
        assignmentDueDate = LocalDate.parse(dueDate);
    }

    public void inflateWidgets(View view) {
        localDate =  LocalDate.of(2022, 2, 28);
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        eventListView = view.findViewById(R.id.eventListView);
        backwardButton = (Button) view.findViewById(R.id.backwardButton);
        forwardButton = (Button) view.findViewById(R.id.forwardButton);
        bottom_nav_bar = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
        bottom_nav_bar.setSelectedItemId(R.id.settingstab);
        monthYearText.setText(monthYearFromDate(localDate));
        days =  daysInWeekArray(localDate);
    }

}