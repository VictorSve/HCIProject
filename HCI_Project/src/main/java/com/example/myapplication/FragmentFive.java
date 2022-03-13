package com.example.myapplication;

import android.graphics.fonts.Font;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class FragmentFive extends Fragment {

    TextView text_view_clock;
    BottomNavigationView bottom_nav_bar;
    Button button_start_pause;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftMillis;
    double selectedTime;
    private long timerTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_5, container, false);
        inflateWidgets(view);

        bottom_nav_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.hometab:
                        return true;
                    case R.id.assignmentstab:
                        ((MainActivity)getActivity()).setViewPager(3);
                        return true;
                    case R.id.settingstab:
                        ((MainActivity)getActivity()).setViewPager(3);
                        return true;
                }
                return false;
            }
        });


        button_start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning) { pauseTimer(); }
                else  { startTimer(); }
            }
        });

        updateCountDownText();
        return view;
    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(timerTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTime = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                button_start_pause.setText("Start");
                button_start_pause.setVisibility(View.INVISIBLE);
            }
        }.start();
        timerRunning = true;
        button_start_pause.setText("pause");
    }
    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        button_start_pause.setText("Start");
    }
    private void updateCountDownText() {
        long time = getTimerTime();
        int minutes = (int) timerTime / 1000 / 60; // makes millisec into minutes
        int second = (int) timerTime / 1000 % 60; // turns time left into seconds
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d : %02d", minutes, second);
        text_view_clock.setText(timeLeftFormatted);
    }

    public void inflateWidgets(View view) {
        text_view_clock = (TextView) view.findViewById(R.id.text_view_clock);
        button_start_pause =(Button) view.findViewById(R.id.button_start_pause);
        bottom_nav_bar = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
        timeLeftMillis = 600000;
        selectedTime = 0.0;
        timerTime = getTimerTime();
    }


    public void update() {
        timerTime = (long) selectedTime * 100;
    }

    public long getTimerTime() {
        try {
            return timerTime;
        }
        catch (NullPointerException e) {
            System.out.println("Sketchy shit");
        }
        return 36000;
    }
}