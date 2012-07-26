package com.jabait.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 6/26/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {

    private Date parseDate(String date, String format)
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
}
