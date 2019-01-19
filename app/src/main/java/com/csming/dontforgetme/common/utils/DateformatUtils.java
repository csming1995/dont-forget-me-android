package com.csming.dontforgetme.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Created by csming on 2019/1/19.
 */
public class DateformatUtils {

    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };

    private static final Date NOW_DATE = new Date();

    public static String formatDate(long date) {
        SimpleDateFormat sdf = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get();
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get();
        return sdf.format(date);
    }

    public static long differentDays(Date date) {
        long duration = NOW_DATE.getTime() - date.getTime();
        return duration / (24 * 60 * 60 * 1000);
    }
}
