/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.dao;

import com.wings4.Login;
import com.wings4.model.*;
import com.wings4.util.FindAllResourceFeed;
import com.wings4.util.InventoryConstants;
import com.wings4.util.RESTFeed;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronnie
 */
public class MaterialDao {
    
    public static List<Category> findAllCategories(){

        List<Category> categories = new ArrayList<Category>();
        try{
            String allCategories = FindAllResourceFeed.restFeedInitialization("category");
            JSONArray jsonArray = new JSONArray(allCategories);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject categoryObject = (JSONObject)jsonArray.get(i);
                Category category = new Category();
                category.setCategoryId(Integer.parseInt(categoryObject.get("id").toString()));
                category.setCategoryName(categoryObject.get("categoryName").toString());
                category.setParentCategory(categoryObject.get("parentCategoryName").toString());

                categories.add(category);
            }
        } catch (JSONException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return categories;
    }
    
    
    public static List<Product> findAllProducts(){
        //return FindAllResourceFeed.restFeedInitialization("product");

        List<Product> products = new ArrayList<Product>();
        try{
            String allProducts = FindAllResourceFeed.restFeedInitialization("product");
            JSONArray jsonArray = new JSONArray(allProducts);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject productObject = (JSONObject)jsonArray.get(i);
                Product product = new Product();
                product.setProductName(productObject.get("productName").toString());
                product.setProductCategory(productObject.get("category").toString());
                product.setProductClassification(productObject.get("classification").toString());
                product.setProductDetails(productObject.get("details").toString());
                product.setStockKeepingUnit(productObject.get("stockKeepingUnit").toString());

                products.add(product);
            }
        } catch (JSONException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return products;
    }


    public static List<InventoryRegister> findAllInventories(){
        List<InventoryRegister> inventoryRegisters = new ArrayList<InventoryRegister>();
        try {
            String allInventories = FindAllResourceFeed.restFeedInitialization("inventory");
            JSONArray jsonArray = new JSONArray(allInventories);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject inventoryObject = (JSONObject)jsonArray.get(i);
                InventoryRegister inventoryRegister = new InventoryRegister();
                
                inventoryRegisters.add(inventoryRegister);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return inventoryRegisters;
    }
    
    public static List<ProductType> findAllProductTypes(){
        List<ProductType> productTypes = new ArrayList<ProductType>();
        try {
            String allProductTypes = FindAllResourceFeed.restFeedInitialization("productType");
            JSONArray jsonArray = new JSONArray(allProductTypes);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productTypeObject = (JSONObject)jsonArray.get(i);
                ProductType productType = new ProductType();
                productTypes.add(productType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return 
        return productTypes;
    }
    
    public static List<ProductClassification> findAllProductClassifications(){
        //return FindAllResourceFeed.restFeedInitialization("productClassification");
        return null;
    }
    
    public static List<UnitOfMeasure> findAllUnitOfMeasures(){
        //return FindAllResourceFeed.restFeedInitialization("unitOfMeasure");
        return null;
    }
}
