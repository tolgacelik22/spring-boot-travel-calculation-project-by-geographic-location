package com.example.myspringproject.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static long getDiffMinutes(String dateStart, String dateStop) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d1 = sdf.parse(dateStart);
        Date d2 = sdf.parse(dateStop);

        long difference = d2.getTime() - d1.getTime();
        long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(difference)% 60;

        return differenceInMinutes;
    }
}
