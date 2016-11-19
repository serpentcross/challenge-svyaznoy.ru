package com.serpentcross.examples.smsservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long getUnixFormat() throws ParseException {
        String formattedDate = formatter.format(new Date());
        return formatter.parse(formattedDate).getTime() / 1000;
    }

    public static String getRegularFormat(Long dbDate) {
        return formatter.format(new Date(dbDate * 1000L));
    }
}
