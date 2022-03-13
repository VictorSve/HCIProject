package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private FragmentFive fragmentFive;
    private CalendarUtils calendarUtils;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_one);
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        calendarUtils = new CalendarUtils();
    }

    /**
     *
     * @param viewPager
     * Adds our fragments to the viewPager, which then adds it to our SectionsStatePagerAdapter
     */
    private void setupViewPager (ViewPager viewPager){
        fragmentThree = new FragmentThree();
        fragmentFive = new FragmentFive();
        fragmentFour = new FragmentFour();
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mSectionsStatePagerAdapter.addFragment(new FragmentOne(), "Fragment 1");
        mSectionsStatePagerAdapter.addFragment(new FragmentTwo(), "Fragment 2");
        mSectionsStatePagerAdapter.addFragment(fragmentThree, "Fragment 3");
        mSectionsStatePagerAdapter.addFragment(fragmentFour, "Fragment 4");
        mSectionsStatePagerAdapter.addFragment(fragmentFive, "Fragment 5");
        viewPager.setAdapter(mSectionsStatePagerAdapter);

    }
    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    /**
     *
     * @param input - String for assignmentDueDate
     * sends the input to be accessed in FragmentThree
     * aswell as CalenderUtils, breaking every design principle
     * possible
     */

    public void updateDueDate(String input) {
        fragmentThree.dueDate = input;
        fragmentThree.update();
        calendarUtils.calendarUtilsDueDate = input;
        calendarUtils.update();

    }

    public void updateSetTime(double input) {
        fragmentFive.selectedTime = input;
        fragmentFive.update();
        fragmentFour.timePerSessionInput = input;
        fragmentFour.updateTwo();
        calendarUtils.inputSessionTime = input;
        calendarUtils.updateTwo();
    }

    public void updateRecyclerSelectedDate(String input) {
        fragmentFour.updatedDateString = input;
        fragmentFour.update();
    }

    public void updateAssignmentNameString(String input) {
        fragmentFour.updatedAssignmentNameString = input;
        fragmentFour.updateThree();
    }
}