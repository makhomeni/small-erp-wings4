package com.jabait.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/10/12
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    /**
     * for date = "Fri Aug 17 23:23:37 BDT 2012", use format = "EEE MMM dd HH:mm:ss zzz yyyy"
     * for date = "Aug 16, 2012", use format  "MMM dd, yyyy"
     * @param date
     * @param format
     * @return
     */


    public Date parseDate(String date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try{
            return formatter.parse(date);
        }catch (ParseException pe){
            pe.printStackTrace();
            System.out.println("Exception occur");
            return null;
        }

    }

    public static void main(String[] args){
        Util util = new Util();
        System.out.println(util.parseDate("Aug 16, 2012", "MMM dd, yyyy"));
    }
}
