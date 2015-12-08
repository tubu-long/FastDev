package com.github.longqiany.fastdev.core.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备相关的方法
 */
public class AndroidUtils {

    /**
     * 用来判断服务是否运行.
     *
     * @param mContext
     * @param clazz 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext, Class clazz) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(clazz.getName()) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 获取imei号码
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        try {
            TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return teleManager.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取配置的版本名和版本号
     *
     * @param context
     * @return
     */
    public static Map<String, Object> getVersionCodeAndName(Context context) {
        Map<String, Object> result = new HashMap<String, Object>();
        String version = null;
        int code = 0;
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
            code = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("vname", version);
        result.put("vcode", code);
        return result;
    }


    /**
     * 获取配置的版本名和版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int code = 0;
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            code = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }

    /**
     * 获取配置的包名
     *
     * @param context
     * @return
     */
    public static String getAppPackage(Context context) {
        String appPackage = null;
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            appPackage = packInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appPackage;
    }

    public static int[] getDeviceWH(Context context) {
        int[] wh = new int[2];
        try {
            int w = 0;
            int h = 0;
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            w = dm.widthPixels;
            h = dm.heightPixels;
            wh[0] = w;
            wh[1] = h;
            context = null;
        } catch (Exception e) {
        }
        return wh;
    }

    public static float getDeviceD(Context context) {
        float d = 0;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            d = dm.density;
            context = null;
        } catch (Exception e) {
        }
        return d;
    }

    public static int dip2px(float density, float dpValue) {
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dip(float density, float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public static int turn(float pxValue, float density, float d) {
        return dip2px(density, px2dip(d, pxValue));
    }

    /**
     * 判断程序是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstall(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 启动已安装APP
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean openApp(Context context, String packageName) {
        try {
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageName);
            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
            if (apps == null || apps.size() < 1) {
                return false;
            }
            ResolveInfo ri = apps.iterator().next();
            String pn = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 启动已安装APP
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean judgeIsScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

}
