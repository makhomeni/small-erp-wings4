/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import com.wings4.Login;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronnie
 */
public class FindAllResourceFeed {
    
    public static String restFeedInitialization(String resource){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.GET,
                Login.getRestEndPoint(), resource);
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
