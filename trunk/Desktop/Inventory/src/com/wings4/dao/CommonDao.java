package com.wings4.dao;

import com.wings4.model.Category;
import com.wings4.model.Organization;
import com.wings4.util.FindAllResourceFeed;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/11/12
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommonDao {


    //for delivery term

    public static String findAllDeliveryTerms(){
        return FindAllResourceFeed.restFeedInitialization("delivery");
    }


    //payment methods

    public static String findAllPaymentMethods(){
        return FindAllResourceFeed.restFeedInitialization("payment");
    }


    public static String findAllPaymentTerms(){
        return FindAllResourceFeed.restFeedInitialization("paymentTerm");
    }

    //user related
    public static String findAllUsers(){
        return FindAllResourceFeed.restFeedInitialization("user");
    }
    
    public static List<Organization>  findAllOrganizations(){

        List<Organization> organizations = new ArrayList<Organization>();
        try{
            String allOrganizations = FindAllResourceFeed.restFeedInitialization("organization");
            JSONArray jsonArray = new JSONArray(allOrganizations);
            System.out.println("length of organization json array" + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject organizationObject = (JSONObject)jsonArray.get(i);
                Organization organization = new Organization();
                organization.setId(Integer.parseInt(organizationObject.get("id").toString()));
                organization.setOrganizationName(organizationObject.get("organizationName").toString());

                organizations.add(organization);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return organizations;
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
}
