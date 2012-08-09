/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.dao;

import com.wings4.Login;
import com.wings4.util.InventoryConstants;
import com.wings4.util.RESTFeed;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author ronnie
 */
public class CategoryDao {
    
    public static String findAllCategories(){
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
