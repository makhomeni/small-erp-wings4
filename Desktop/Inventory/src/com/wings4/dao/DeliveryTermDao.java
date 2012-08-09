package com.wings4.dao;

import com.wings4.Login;
import com.wings4.util.InventoryConstants;
import com.wings4.util.RESTFeed;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/10/12
 * Time: 1:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeliveryTermDao {
    private String getRestEndPoint(){
        return Login.getRestEndPoint();
    }


    public String findAllDeliveryTerm(){
        String restEndPoint = getRestEndPoint();
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, restEndPoint, "delivery");
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
