/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.dao;

import com.wings4.Login;
import com.wings4.model.*;
import com.wings4.util.FindAllResourceFeed;
import com.wings4.util.InventoryConstants;
import com.wings4.util.POSTResourceFeed;
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

    public static boolean saveCategory(Category category){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categoryName", category.getCategoryName());
            System.out.println("category.getParentCategory() = " + category.getParentCategory());
            jsonObject.put("parentCategory", category.getParentCategory());
            POSTResourceFeed.post("category", jsonObject);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<Category> findAllCategories(){

        List<Category> categories = new ArrayList<Category>();
        try{
            String allCategories = FindAllResourceFeed.restFeedInitialization("category");
            JSONArray jsonArray = new JSONArray(allCategories);
            System.out.println("jsonArray.length() = " + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject categoryObject = (JSONObject)jsonArray.get(i);
                Category category = new Category();
                category.setCategoryId(Integer.parseInt(categoryObject.get("id").toString()));
                category.setCategoryName(categoryObject.get("categoryName").toString());
                String parentCategory = "";
                if(categoryObject.get("parentCategory") != null ){
                    System.out.println("parentCategory = " + parentCategory);
                    parentCategory = categoryObject.get("parentCategory").toString();
                }


                category.setParentCategory(parentCategory.equals("") ? "" : parentCategory);

                categories.add(category);
            }
        } catch (JSONException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }


        return categories;
    }

//    public static List<Vendors>
    
    
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
            String allProductTypes = FindAllResourceFeed.restFeedInitialization("productUtil?utilType=productType");
            JSONArray jsonArray = new JSONArray(allProductTypes);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productTypeObject = (JSONObject)jsonArray.get(i);
                ProductType productType = new ProductType();
                productType.setProductTypeId(Integer.parseInt(productTypeObject.get("id").toString()));
                productType.setName(productTypeObject.get("name").toString());
                productType.setDescription(productTypeObject.get("description").toString());
                productTypes.add(productType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return 
        return productTypes;
    }
    
    public static List<ProductClassification> findAllProductClassifications(){
        List<ProductClassification> classifications = new ArrayList<ProductClassification>();
        try {
            String allProductClassifications = FindAllResourceFeed.restFeedInitialization("productUtil?utilType=classification");
            JSONArray jsonArray = new JSONArray(allProductClassifications);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject classificationObject = (JSONObject)jsonArray.get(i);
                ProductClassification productClassification = new ProductClassification();
                productClassification.setClassificationId(Integer.parseInt(classificationObject.get("id").toString()));
                productClassification.setClassification(classificationObject.get("classification").toString());
                productClassification.setDescription(classificationObject.get("description").toString());
                classifications.add(productClassification);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return
        return classifications;
    }
    
    public static List<UnitOfMeasure> findAllUnitOfMeasures(){
        List<UnitOfMeasure> uoms = new ArrayList<UnitOfMeasure>();
        try {
            String allUnitOfMeasures = FindAllResourceFeed.restFeedInitialization("productUtil?utilType=uom");
            JSONArray jsonArray = new JSONArray(allUnitOfMeasures);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject uomObject = (JSONObject)jsonArray.get(i);
                UnitOfMeasure uom = new UnitOfMeasure();
                uom.setId(Integer.parseInt(uomObject.get("id").toString()));
                uom.setUom(uomObject.get("uom").toString());
                uom.setDescription(uomObject.get("description").toString());
                uoms.add(uom);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return
        return uoms;
    }
}
