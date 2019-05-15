package com.scrypt.sfax.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class DateUtils {
  // 07/15/2016 1:17:20 PM
  private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa", Locale.ENGLISH);
  // 1:17:20 PM
  private static DateFormat timeOnly = new SimpleDateFormat("hh:mm:ss aa", Locale.ENGLISH);

  private static long lengthOfDay = 86400000;

  public static Date fromString(String date) {
    try {
      return df.parse(date);
    } catch (ParseException e) {
      Timber.e(e);
      return null;
    }
  }

  public static String getDateLabel(Date date) {
    Date now = new Date();
    return calculateDateLabel(now, date);
  }

  public static String calculateDateLabel(Date from, Date to) {
    long delta = Math.abs(from.getTime() - to.getTime());

    // More than 2 days ago always
    if(delta > 2 * lengthOfDay) {
      return df.format(to);
    }
    // Else if today
    if(to.getDate() == from.getDate()) {
      return timeOnly.format(to);
    }
    // Else is same month but next day
    if(to.getMonth() == from.getMonth() && to.getDate() - from.getDate() == 1) {
      return "Yesterday";
    }
    // Else month has changed
    if(from.getDay() - to.getDay() == 1) {
      return "Yesterday";
    }
    // Else is first day of new year
    if(from.getDay() == 0 && to.getMonth() == 11 && to.getDate() == 31) {
      return "Yesterday";
    }
    return df.format(to);
  }
}
