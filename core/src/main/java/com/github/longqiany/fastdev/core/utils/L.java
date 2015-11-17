package com.github.longqiany.fastdev.core.utils;

import android.util.Log;

/**
 * 输出日志开关。上线时关闭
 *
 * @author zzz
 */

public class L {

    private static boolean isDebug = true;
//        private static boolean isDebug = false;
    private static final String TAG = "TAG";

    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void v(Class<?> clz, String msg) {
        if (isDebug) Log.v(clz.getSimpleName().toString(), msg);
    }

    public static void d(Class<?> clz, String msg) {
        if (isDebug) Log.d(clz.getSimpleName().toString(), msg);
    }

    public static void i(Class<?> clz, String msg) {
        if (isDebug) Log.i(clz.getSimpleName().toString(), msg);
    }

    public static void w(Class<?> clz, String msg) {
        if (isDebug) Log.w(clz.getSimpleName().toString(), msg);
    }

    public static void e(Class<?> clz, String msg) {
        if (isDebug) Log.e(clz.getSimpleName().toString(), msg);
    }

    public static void v(String clz, String msg) {
        if (isDebug) Log.v(clz, msg);
    }

    public static void d(String clz, String msg) {
        if (isDebug) Log.d(clz, msg);
    }

    public static void i(String clz, String msg) {
        if (isDebug) Log.i(clz, msg);
    }

    public static void w(String clz, String msg) {
        if (isDebug) Log.w(clz, msg);
    }

    public static void e(String clz, String msg) {
        if (isDebug) Log.e(clz, msg);
    }


    public static void E(Class<?> clz, Throwable throwable) {
        Log.e(clz.getClass().getName(), clz.getClass().getName() + " / " + throwable.getMessage(), throwable);
    }

    public static void E(String clz, Throwable throwable) {
        Log.e(clz, clz.getClass().getName() + " / " + throwable.getMessage(), throwable);
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }
}
