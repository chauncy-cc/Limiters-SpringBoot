package com.chaney.limiters.limiters;

import java.util.Timer;
import java.util.TimerTask;

public class CountLimiter {

    private final long msec;
    private long remainSec;
    private final int count;     // 先用int，应该会发生超出负载
    private int remainCount;

    private CountLimiter(long msec, int count) {
        this.msec = msec;
        this.count = count;
        this.remainSec = msec;
        this.remainCount = count;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remainCount = count;
            }
        }, 0, msec);
    }

    public static CountLimiter create(long msec, int count) {
        return new CountLimiter(msec, count);
    }

    public boolean acquire() {
        if (remainCount > 0) {
            remainCount--;
            return true;
        }  else {
            return false;
        }
    }
}
