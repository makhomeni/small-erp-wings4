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
 * Date: 8/11/12
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommonDao {


    //for delivery term

    public String findAllDeliveryTerm(){
        return restFeedInitialization("delivery");
    }


    //payment methods

    public String findAllPaymentMethods(){
        return restFeedInitialization("payment");
    }


    public String findAllPaymentTerms(){
        return restFeedInitialization("paymentTerm");
    }

    //user related
    public String findAllUsers(){
        return restFeedInitialization("user");
    }
    
    public String restFeedInitialization(String resource){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.GET,
                Login.getRestEndPoint(), resource);
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
