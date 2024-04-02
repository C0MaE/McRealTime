package com.comae.mcrealtime.methods;

import java.time.LocalDateTime;

public class TimeMethods {

    public static Long toMcTime(LocalDateTime localDateTime) {
        // ((12*1000)-6000)+(00*16.6)=6000
        return Math.round((localDateTime.getHour() * 1000 - 6000) + (localDateTime.getMinute() * 16.6));
    }

    public static void setTime() {

    }
}
