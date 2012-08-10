/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author ronnie
 */
public class UserDao {
    public static String findAllUsers(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.GET,
                Login.getRestEndPoint(), "user");
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
