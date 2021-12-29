package handlers;

import java.io.File;
import java.math.BigDecimal;


public class FormatTextHandler {

    public static String formatFilename(File file) {
        return file.toString().replace("src\\test\\downloadedTestFiles\\", "");
    }

    public static Integer getIntFromString(String string){
        return Integer.parseInt(string.replaceAll("[\\D]", ""));
    }
    public static BigDecimal getBigDecimalFromString(String string){
       return new BigDecimal(string.replace("zł", ""));
    }

    public static String trimProductName(String string){
        return string.split(" -")[0].trim();
    }

    public static String trimText(String string){
        return string.substring(string.indexOf(":")+1).trim();
    }
}