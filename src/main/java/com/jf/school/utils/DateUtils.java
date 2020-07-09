package com.jf.school.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static long getUnixTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getUnixTimeStamp(long time){
        return time / 1000;
    }


    public static Long getFirstDayOfMoth(String date) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy年MM月").parse(date));
        int firstDay=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份最小的天数
        calendar.set(Calendar.DAY_OF_MONTH,firstDay);
        return DateUtils.getUnixTimeStamp(calendar.getTimeInMillis());
    }
    public static Long getLastDayOfMoth(String date) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy年MM月").parse(date));
        int lastDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份最大的天数
        calendar.set(Calendar.DAY_OF_MONTH,lastDay);
        return DateUtils.getUnixTimeStamp(calendar.getTimeInMillis());
    }

}
