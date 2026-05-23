package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonMethods {
    public static Date RefactorDate(String inputDate, Integer addDays) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setTime(sdf.parse(inputDate));
        cl.add(Calendar.DAY_OF_MONTH, addDays);
        String newDate = sdf.format(cl.getTime());
        return sdf.parse(newDate);
    }
}