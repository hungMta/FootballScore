package com.hungtran.footballscore.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
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
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013
        String dayString = day + "/" + monthNumber + "/" + year;
        return dayString;
    }

    public String getTime(Date date) {
        int hour = date.getHours();
        int minute = date.getMinutes();
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        String time = localDateFormat.format(date);
        String timeString = hour + ":" + minute;
        return time;
    }

}
