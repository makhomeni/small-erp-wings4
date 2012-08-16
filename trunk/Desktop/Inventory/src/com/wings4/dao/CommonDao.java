package com.wings4.dao;

import com.wings4.util.FindAllResourceFeed;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/11/12
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommonDao {


    //for delivery term

    public static String findAllDeliveryTerms(){
        return FindAllResourceFeed.restFeedInitialization("delivery");
    }


    //payment methods

    public static String findAllPaymentMethods(){
        return FindAllResourceFeed.restFeedInitialization("payment");
    }


    public static String findAllPaymentTerms(){
        return FindAllResourceFeed.restFeedInitialization("paymentTerm");
    }

    //user related
    public static String findAllUsers(){
        return FindAllResourceFeed.restFeedInitialization("user");
    }
    
    public static String findAllOrganizations(){
        return FindAllResourceFeed.restFeedInitialization("organization");
    }
}
