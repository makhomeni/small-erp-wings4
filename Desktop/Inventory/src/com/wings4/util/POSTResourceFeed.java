package com.wings4.util;

import com.wings4.Login;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/31/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class POSTResourceFeed {
    
    public static boolean post(String resource, JSONObject jsonObject){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.POST,
                Login.getRestEndPoint(), resource);
        restFeed.setJsonObject(jsonObject);
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            restFeed.restInitialization();
            return true;
        } catch (MalformedURLException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
}
