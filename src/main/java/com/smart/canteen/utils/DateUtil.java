package com.smart.canteen.utils;

import com.smart.canteen.dto.DateDTO;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/29下午 4:19
 */
public class DateUtil {

    private static Calendar getNow() {
        return Calendar.getInstance();
    }

    public static DateDTO getYear() {
        Calendar calendar = getNow();
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date start = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date end = calendar.getTime();
        return new DateDTO(start, end);
    }


    public static DateDTO getMonth() {
        Calendar calendar = getNow();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        Date start = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date end = calendar.getTime();
        return new DateDTO(start, end);
    }

    public static DateDTO getDay() {
        Calendar calendar = getNow();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date end = calendar.getTime();
        return new DateDTO(start, end);
    }
}
