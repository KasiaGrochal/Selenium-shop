package handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHandler {

    public static String getCurrentDateInMMddYYYY(){
        return formatDateToddMMyyyy(getCurrentDate());
    }

    public static String formatDateToMMddYyyy(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
    public static String formatDateToddMMyyyy(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }

    public static Date formatDateFromString(String string) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMM/dd/yyyy", Locale.UK);
        Date date = dateFormat.parse(string);
        return date;
    }

    public static String formatDateToYearOnly(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public static int getMonthAsInt(String month, Locale locale) throws ParseException {
        Date date = new SimpleDateFormat("MMM", locale).parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentMonthAsInt() {
        Date date = DateHandler.getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getCurrentYearAsInt() {
        return Integer.parseInt(DateHandler.formatDateToYearOnly(DateHandler.getCurrentDate()));
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
