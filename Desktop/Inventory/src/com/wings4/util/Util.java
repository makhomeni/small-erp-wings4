package com.wings4.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/10/12
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {
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
