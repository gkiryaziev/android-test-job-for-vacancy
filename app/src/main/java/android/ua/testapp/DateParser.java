package android.ua.testapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    // parse unixtimestamp to formated string
    public static String unixToFormatedString(String format, long timestamp) throws Exception {
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        return simpleDateFormat.format(date);
    }
}
