package com.ivanxc.netcracker.lab.chapter9.programmingprojects.clock;

import java.time.LocalTime;

public class Clock {
    private String alarmHours;
    private String alarmMinutes;

    public void setAlarm(int hours, int minutes) {
        alarmHours = String.valueOf(hours);
        alarmMinutes = String.valueOf(minutes);
    }

    public String getHours() {
        return LocalTime.now().toString().substring(0, 2);
    }

    public String getMinutes() {
        return LocalTime.now().toString().substring(3, 5);
    }

    public String getTime() {
        String hours = getHours();
        String minutes = getMinutes();
        String time = hours + ":" + minutes;

        if (alarmHours != null && alarmMinutes != null) {
            if (alarmHours.compareTo(hours) < 0 ||
                alarmHours.compareTo(hours) == 0 && alarmMinutes.compareTo(minutes) <= 0) {
                time += "\u23F0";
            }
            alarmHours = null;
            alarmMinutes = null;
        }
        return time;
    }
}
