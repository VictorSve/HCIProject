package com.example.myapplication;

public class Assignment {
    public String clockTime;
    public String name;


    public Assignment(String name, String time) {
        this.clockTime = time;
        this.name = name;
    }

    public Assignment(String time) {
        this.clockTime = time;
    }
    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
