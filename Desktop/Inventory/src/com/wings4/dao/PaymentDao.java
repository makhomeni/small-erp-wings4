package com.wings4.dao;

import com.wings4.Login;
import com.wings4.client.Category;
import com.wings4.util.InventoryConstants;
import com.wings4.util.RESTFeed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/9/12
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDao {
    
    public static String findAllPayments(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "payment");
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }

    }
}
