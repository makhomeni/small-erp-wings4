package com.wings4.dao;

import com.wings4.model.Category;
import com.wings4.model.Organization;
import com.wings4.model.ShippingMethod;
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

    public static List<ShippingMethod>  findAllShippingMethods(){

        List<ShippingMethod> shippingMethods = new ArrayList<ShippingMethod>();
        try{
            String allShippingMethods = FindAllResourceFeed.restFeedInitialization("shippingMethod");
            JSONArray jsonArray = new JSONArray(allShippingMethods);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject shippingMethodObject = (JSONObject)jsonArray.get(i);
                ShippingMethod shippingMethod = new ShippingMethod();
                shippingMethod.setId(Integer.parseInt(shippingMethodObject.get("id").toString()));
                shippingMethod.setShippingMethod(shippingMethodObject.get("shippingMethod").toString());

                shippingMethods.add(shippingMethod);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return shippingMethods;
    }
}
