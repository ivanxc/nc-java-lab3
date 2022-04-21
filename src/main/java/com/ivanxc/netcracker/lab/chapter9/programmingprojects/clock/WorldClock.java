package com.ivanxc.netcracker.lab.chapter9.programmingprojects.clock;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.clock.Clock;
import java.time.LocalTime;

public class WorldClock extends Clock {
    private int timeOffset;

    public WorldClock(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    @Override
    public String getHours() {
        return LocalTime.now().plusHours(timeOffset).toString().substring(0, 2);
    }
}
