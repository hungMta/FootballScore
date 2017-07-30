package com.hungtran.footballscore.utils;

import android.util.Log;

/**
 * Created by PC on 20/07/2017.
 */

public class Logg {

    public static void debug(Class class_, String message) {
        String TAG = "football_" + class_.getSimpleName();
        Log.d(TAG, message);
    }

    public static void error(Class class_, String message) {
        String TAG = "football_" + class_.getSimpleName();
        Log.e(TAG, message);
    }
}
