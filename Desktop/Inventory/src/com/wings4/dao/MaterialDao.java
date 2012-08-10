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
public class MaterialDao {
    
    public static String findAllCategories(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "category");
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    
    
    public static String findAllProducts(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "product");
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static String findAllProductTypes(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "productType");
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static String findAllProductClassifications(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "productClassification");
        try {
            System.out.println("restFeed.restInitialization() = " + restFeed.restInitialization());
            return restFeed.restInitialization();
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static String findAllUnitOfMeasures(){
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON, InventoryConstants.MEDIA_JSON,
                InventoryConstants.GET, Login.getRestEndPoint(), "unitOfMeasure");
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
