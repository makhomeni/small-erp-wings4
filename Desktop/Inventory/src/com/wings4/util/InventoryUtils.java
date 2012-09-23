package com.wings4.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/23/12
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryUtils {

    public static String replaceSpecialCharacter(String value) {
        String replacedCharacter = value;
        try {
            replacedCharacter = replacedCharacter.replaceAll(" ", "_");
            replacedCharacter = replacedCharacter.replaceAll("&|#|<|>|:|;|=|/|\\[|\\]|\\*|\\?|\\\\|\\||\"", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return replacedCharacter;
    }

    public static Date getLastDate() {
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, maxDay);
        SimpleDateFormat sdf = new SimpleDateFormat(InventoryConstants.DATE_FORMAT);
        return cal.getTime();
    }

    public static Date getFirstDate() {
        Calendar cal = Calendar.getInstance();
        int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, minDay);
        SimpleDateFormat sdf = new SimpleDateFormat(InventoryConstants.DATE_FORMAT);
        return cal.getTime();
    }

    public static int getToday() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    public static Time getTimeInTimeFormat(String Format, String time) {
        DateFormat sdf = null;
        Date date = null;
        Time timeObject = null;
        try {
            sdf = new SimpleDateFormat(Format);
            date = sdf.parse(time);
            timeObject = new Time(date.getTime());
        } catch (ParseException ex) {
        } finally {
            sdf = null;
            date = null;
        }
        return timeObject;
    }

    public static String getDateAndTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(InventoryConstants.DATE_AND_TIME_FORMAT);
        return sdf.format(cal.getTime());
    }

    public static String getMonth() {
        String monthStr = null;
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        //int module = month % 10;
        int length = String.valueOf(month).length();
        System.out.println(8%10 + "....." + 12%10);
        if (length < 1) {
            System.out.println("inside if");
            //JOptionPane.showConfirmDialog(null, month+"--"+module);
            monthStr = String.valueOf("0").concat(String.valueOf(month));
        }else{
            monthStr = String.valueOf(month);
        }
        return monthStr;
    }

    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        int tenPlace = thisYear % 100;
        return tenPlace;
    }

    public static boolean stringOrNumericCheck(String parma) {
        int numericChk;
        boolean numericChkStr = true;
        try {

            numericChk = Integer.parseInt(parma);
        } catch (Exception ex) {

            numericChkStr = false;
        }
        return numericChkStr;
    }

    public static Date getInDateFormat(Date date, Time time) {
        //GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
        Calendar calendarForDate = new GregorianCalendar();
        Calendar calendarForTime = new GregorianCalendar();
        calendarForDate.setTime(date);
        calendarForTime.setTime(time);

        GregorianCalendar gc = new GregorianCalendar(calendarForDate.get(Calendar.YEAR), calendarForDate.get(Calendar.MONTH), calendarForDate.get(Calendar.DATE), calendarForTime.get(Calendar.HOUR), calendarForTime.get(Calendar.MINUTE), calendarForTime.get(Calendar.SECOND));
        Date d = gc.getTime();

        return d;
    }

    public static Date getInDateFormat(String format, String dateandTime) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(dateandTime);
        } catch (ParseException ex) {
        }
        return d;
    }

    public static String getDateInFormat(String format, Date date) {
        DateFormat df = new SimpleDateFormat(format);
        String ls_return = null;
        try {
            ls_return = df.format(date);
        } catch (Exception ex) {
        }
        return ls_return;
    }

    public static String getTimeInFormat(String format, Time time) {
        DateFormat df = new SimpleDateFormat(format);
        String ls_return = null;
        try {
            ls_return = df.format(time);
        } catch (Exception ex) {
        }
        return ls_return;
    }

    public static String findEmailName(long autoTicketId) {
        return String.valueOf(autoTicketId) + "_" + String.valueOf(System.currentTimeMillis()) + ".eml";
    }

    public static String padString(String value, boolean prefix, String padChar, int totalLength) {
        String retVal = "";
        int padLength = 0;
        try {
            if (value != null) {
                padLength = totalLength - value.length();
                if (padLength > 0) {
                    for (int i = 0; i < padLength; i++) {
                        retVal += padChar;
                    }
                    retVal = retVal + value;
                } else {
                    retVal = value;
                }
            } else {
                retVal = value;
            }
        } catch (Exception e) {
        }
        return retVal;
    }

    public static String nullToString(String value) {
        return (value == null ? "" : value);
    }

    public static void createDirIfNotExists(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
    }

    public static byte[] getFileContentAsByteArray(String url) {
        StringBuffer text = null;
        BufferedReader reader = null;
        String line = null;
        String content = null;
        try {
            text = new StringBuffer();
            reader = new BufferedReader(new FileReader(url));
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(System.getProperty("line.separator"));
            }
            content = text.toString();
        } catch (Exception ex) {
        } finally {
            try {
                text = null;
                reader.close();
            } catch (IOException ex) {
            }
        }
        return content.getBytes();
    }

    public static String getFileContentAsString(String url) {
        StringBuffer text = null;
        BufferedReader reader = null;
        String line = null;
        String content = null;
        try {
            text = new StringBuffer();
            reader = new BufferedReader(new FileReader(url));
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(System.getProperty("line.separator"));
            }
            content = text.toString();
        } catch (Exception ex) {
        } finally {
            try {
                text = null;
                reader.close();
            } catch (IOException ex) {
            }
        }
        return content;
    }

    public static String getFileContentAsString(File file) {
        StringBuffer text = null;
        BufferedReader reader = null;
        String line = null;
        String content = null;
        try {
            text = new StringBuffer();
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(System.getProperty("line.separator"));
            }
            content = text.toString();
        } catch (Exception ex) {
        } finally {
            try {
                text = null;
                reader.close();
            } catch (IOException ex) {
            }
        }
        return content;
    }
}
