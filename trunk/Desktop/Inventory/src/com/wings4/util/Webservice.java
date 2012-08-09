package com.wings4.util;

import com.wings4.Login;
import com.wings4.client.Category;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/9/12
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */

public class Webservice {





    public String getRestEndPoint() {
        return Login.getRestEndPoint();
    }

    public String getDataFromWeb(String resource, String requestType){
        String restEndPoint = getRestEndPoint();

        String serviceUri = restEndPoint.concat(resource);

        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,InventoryConstants.MEDIA_JSON,
                requestType);

        restFeed.setRestEndPoint(serviceUri);
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
