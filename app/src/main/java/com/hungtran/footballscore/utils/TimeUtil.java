package com.hungtran.footballscore.utils;

import android.content.Context;

import java.util.Date;

/**
 * Created by Hung Tran on 7/22/2017.
 */

public class TimeUtil {

    private static TimeUtil timeUtil;
    private static Context mContext;

    public static   TimeUtil newInstace(Context context){
        mContext = context;
        if (timeUtil == null){
            timeUtil = new TimeUtil();
        }
        return timeUtil;
    }

    public String getDay(Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        String dayString = day + "/" + month + "/" + year;
        return dayString;
    }

    public String getTime(Date date) {
        int hour = date.getHours();
        int minute = date.getMinutes();
        String timeString = hour + ":" + minute;
        return timeString;
    }

}
