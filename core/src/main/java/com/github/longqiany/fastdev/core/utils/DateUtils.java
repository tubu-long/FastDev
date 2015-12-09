package com.github.longqiany.fastdev.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zzz on 14-12-16.
 */
public class DateUtils {

    public static final SimpleDateFormat SDF = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static final String SDF_PATTERN_1 = "yyyy年MM月dd";

    public static Date stringToDateTime(String strDate) {
        if (strDate != null) {
            try {
                return SDF.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取两个时间之差
     *
     * @param strtime1
     * @param strttime2
     * @param pattern
     * @return
     */
    public static int differentDay(String strtime1, String strttime2,
                                   String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date1 = null;
        try {
            date1 = sdf.parse(strtime1);
            Date date2 = sdf.parse(strttime2);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean compareTime(String strtime1, String strttime2,
                                      String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date1 = null;
        try {
            date1 = sdf.parse(strtime1);
            Date date2 = sdf.parse(strttime2);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();

            return (time2 - time1) > 0 ? true : false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取时间之差
     *
     * @param from
     * @param to
     * @param pattern
     * @return 【1,2】 差一年两个月
     */
    public static int[] getDateLength(String from, String to, String pattern) {
//		java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
        //java.util.Calendar c1 = java.util.Calendar.getInstance();
        //java.util.Calendar c2 = java.util.Calendar.getInstance();
//		try {
//			c1.setTime(df.parse(from));
//			c2.setTime(df.parse(to));
//		} catch (java.text.ParseException e) {
//			e.printStackTrace();
//		}
        int[] result = new int[2];
        Long[] monthsize = getAllMonths(from, to, pattern);
        if (monthsize != null && monthsize.length > 0) {
            result[0] = monthsize.length / 12;
            result[1] = monthsize.length % 12;
        }
        return result;
    }


    /**
     * 获取所有月份
     * @param from
     * @param to
     * @param pattern
     * @return
     */
    protected static Long[] getAllMonths(String from, String to, String pattern) {
        List<Long> months = new ArrayList<Long>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        java.text.DateFormat df = new SimpleDateFormat(pattern);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(from));
            c2.setTime(df.parse(to));
            int i = 0;
            while (c1.compareTo(c2) <= 0) {
                i ++;
                c1.setTime(df.parse(from));
                c1.add(Calendar.MONTH, i);// 开始日期加一个月直到等于结束日期为止
                Date ss = c1.getTime();
                Long str = Long.valueOf(sdf.format(ss));
                months.add(str);
                System.out.println( i + "====months====" + str);
            }

            Long[] str = new Long[months.size()];
            for (i = 0; i < months.size(); i++) {
                str[i] = (Long) months.get(i);
            }
            return str;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }




}
