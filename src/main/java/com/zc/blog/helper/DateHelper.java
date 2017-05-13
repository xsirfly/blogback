package com.zc.blog.helper;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangcong on 2017/5/11.
 */
public class DateHelper {

    public static final String                        DT_PATTERN   = "yyyy-MM-dd HH:mm:ss";
    public static final String                        DATE_PATTERN = "yyyy-MM-dd";
    public static final String                        TIME_PATTERN = "HH:mm:ss";

    public static final ThreadLocal<SimpleDateFormat> SDF          = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DT_PATTERN);
        }
    };

    public static final ThreadLocal<SimpleDateFormat> SDF_DATE     = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_PATTERN);
        }
    };

    public static final ThreadLocal<SimpleDateFormat> SDF_TIME     = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TIME_PATTERN);
        }
    };

    public static Date parse(String dateStr) {
        return parse(dateStr, SDF.get());
    }

    public static Date parseDate(String dateStr) {
        return parse(dateStr, SDF_DATE.get());
    }

    public static Date parseTime(String dateStr) {
        dateStr = formatDate(new Date()) + " " + dateStr;
        return parse(dateStr);
    }

    private static Date parse(String dateStr, SimpleDateFormat sdf) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(Date date) {
        return format(date, SDF.get());
    }

    public static String formatDate(Date date) {
        return format(date, SDF_DATE.get());
    }

    public static String formatTime(Date date) {
        return format(date, SDF_TIME.get());
    }

    private static String format(Date date, SimpleDateFormat sdf) {
        return date == null ? null : sdf.format(date);
    }
}
