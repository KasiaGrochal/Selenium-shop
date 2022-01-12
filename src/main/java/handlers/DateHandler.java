package handlers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    public static String getCurrentDateInMMddYYYY() {
        return formatDateToddMMyyyy(getCurrentDate());
    }

    public static String formatDateToddMMyyyy(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }

    public static Date getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public static String getCurrentDateForFileNames() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        return formatter.format(date);
    }

}
