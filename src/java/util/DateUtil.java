package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
  public static Date parseStringToDate(String pattern, String date) {
    try {
      return (new SimpleDateFormat(pattern)).parse(date);
    } catch (ParseException e) {
      System.out.println("Unable to Parse Date : Pattern - " + pattern + " Date - " + date);
      return null;
    } 
  }
  
  public static String formatDate(String pattern, Date date) {
    return (new SimpleDateFormat(pattern)).format(date);
  }
}
