package com.example.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class FragmentTwo extends Fragment implements AdapterView.OnItemSelectedListener{

    private Button add_assignment_button;
    private Button date_button;
    private EditText editText1;
    private EditText editText2;
    private EditText editText4;
    DatePickerDialog.OnDateSetListener setListener;
    private Spinner spinner;
    private boolean firstTime = true;
    BottomNavigationView bottom_nav_bar;
    double time;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_2, container, false);
        inflateWidgets(view);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DATE);
        System.out.println(time + " uuu");

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
                    case R.id.settingstab:
                        ((MainActivity)getActivity()).setViewPager(0);
                        return true;
                }
                return false;
            }
        });


        add_assignment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText1.getText().toString();
                ((MainActivity)getActivity()).updateAssignmentNameString(s);
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        /**
         * Opens the DatePicker widget whenever the date_button
         * is clicked
         */
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_DeviceDefault_DayNight, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
            }
        });

        /**
         * Adds a listener to the DatePicker widget
         * edits the edittext2 textfield to the date
         * selected in format yy/mm/dd
         * Sends the date to updateDueDate() method in
         * our MainActivity class, so that this value
         * can be accessed in other fragments
         */

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = year + "-0" + month + "-" +day;
                editText2.setText(date);
                ((MainActivity)getActivity()).updateDueDate(date);

            }
        };



        return view;
    }

    /**
     * Decides what happends when an item is selected in the Spinner
     */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String text = parent.getItemAtPosition(i).toString();
        if(firstTime)
            firstTime = false;
        else {
            editText4.setText(text);

            if(text.equals("3 hours")) {
                time = 3 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
            if(text.equals("2.5 hours")) {
                time = 2.5 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
            if(text.equals("2 hours")) {
                time = 2 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
            if(text.equals("1.5 hours")) {
                time = 1.5 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
            if(text.equals("1 hours")) {
                time = 1 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
            if(text.equals("0.5 hours")) {
                time = 0.5 * 36000;
                ((MainActivity)getActivity()).updateSetTime(time);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    public void inflateWidgets(View view) {
        bottom_nav_bar = view.findViewById(R.id.bottom_nav_bar);
        add_assignment_button = (Button) view.findViewById(R.id.add_assignment_button);
        date_button = (Button) view.findViewById(R.id.date_button);
        editText1 = view.findViewById(R.id.assignmentNameEditText);
        editText2 = view.findViewById(R.id.dueDateEditText);
        editText4 = view.findViewById(R.id.timePerSessionEditText);
        spinner = view.findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setPrompt("time");
        bottom_nav_bar = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
        bottom_nav_bar.setSelectedItemId(R.id.settingstab);
        time = 0.0;
    }

}
