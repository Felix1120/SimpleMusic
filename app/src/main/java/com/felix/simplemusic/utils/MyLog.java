package com.felix.simplemusic.utils;

import android.util.Log;

/**
 * Created by chaofei.xue on 2018/8/8.
 */

public class MyLog {
    private final static boolean IS_INFO = true;
    private final static boolean IS_DEBUG = true;

    public static void info(String... values) {
        if (IS_INFO) {
            if (values.length > 1) {
                Log.i("Felix", "info  " + values[0] + " : " + values[1]);
                return;
            } else {
                Log.i("Felix", "info  " + values[0]);
                return;
            }
        }
    }

    public static void debug(String... values) {
        if (IS_DEBUG) {
            if (values.length > 1) {
                Log.d("Felix", "debug  " + values[0] + ":" + values[1]);
                return;
            } else {
                Log.d("Felix", "debug  " + values[0]);
                return;
            }
        }
    }
}
