/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.dao;

import com.wings4.Login;
import com.wings4.util.FindAllResourceFeed;
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
        return FindAllResourceFeed.restFeedInitialization("category");
    }
    
    
    public static String findAllProducts(){
        return FindAllResourceFeed.restFeedInitialization("product");
    }
    
    public static String findAllProductTypes(){
        return FindAllResourceFeed.restFeedInitialization("productType");
    }
    
    public static String findAllProductClassifications(){
        return FindAllResourceFeed.restFeedInitialization("productClassification");
    }
    
    public static String findAllUnitOfMeasures(){
        return FindAllResourceFeed.restFeedInitialization("unitOfMeasure");
    }
}
