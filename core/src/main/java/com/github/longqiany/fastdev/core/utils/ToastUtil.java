package com.github.longqiany.fastdev.core.utils;

import android.content.Context;
import android.widget.Toast;

import com.github.longqiany.fastdev.core.FastDevApplication;

public class ToastUtil {

    public static void show(String info) {
        Toast.makeText(FastDevApplication.getInstance(), info, Toast.LENGTH_LONG).show();
    }

    public static void show(int rid) {
        Toast.makeText(FastDevApplication.getInstance(), rid, Toast.LENGTH_LONG).show();
    }

    public static void showS(String info) {
        Toast.makeText(FastDevApplication.getInstance(), info, Toast.LENGTH_SHORT).show();
    }

    public static void showS(int rid) {
        Toast.makeText(FastDevApplication.getInstance(), rid, Toast.LENGTH_SHORT).show();
    }


    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void showS(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void showS(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
    public static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
}
