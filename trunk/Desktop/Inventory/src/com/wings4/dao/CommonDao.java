package com.wings4.dao;

import com.wings4.model.*;
import com.wings4.util.FindAllResourceFeed;
import com.wings4.util.POSTResourceFeed;
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

    public static List<DeliveryTerm> findAllDeliveryTerms(){
        List<DeliveryTerm> deliveryTerms = new ArrayList<DeliveryTerm>();
        String allDeliveryTerms = FindAllResourceFeed.restFeedInitialization("delivery");
        try {
            JSONArray jsonArray = new JSONArray(allDeliveryTerms);
            for(int i =0; i < jsonArray.length(); i++){
                JSONObject deliveryTermsObject = (JSONObject)jsonArray.get(i);
                DeliveryTerm deliveryTerm = new DeliveryTerm();
                deliveryTerm.setId(Integer.parseInt(deliveryTermsObject.get("id").toString()));
                deliveryTerm.setTerms(deliveryTermsObject.get("terms").toString());
                deliveryTerms.add(deliveryTerm);
            }
        } catch (JSONException e) {
            e.printStackTrace();  
        }

        return deliveryTerms;
    }


    //payment methods

    public static String findAllPaymentMethods(){
        return FindAllResourceFeed.restFeedInitialization("payment");
    }


    public static List<PaymentTerm> findAllPaymentTerms(){
        List<PaymentTerm> paymentTerms = new ArrayList<PaymentTerm>();
        try{
            String allPaymentTerms = FindAllResourceFeed.restFeedInitialization("paymentTerm");
            JSONArray jsonArray = new JSONArray(allPaymentTerms);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject paymentTermsObject = (JSONObject)jsonArray.get(i);
//                Organization organization = new Organization();
                PaymentTerm paymentTerm = new PaymentTerm();
                paymentTerm.setPaymentTermId(Integer.parseInt(paymentTermsObject .get("id").toString()));
                paymentTerm.setName(paymentTermsObject .get("name").toString());

                paymentTerms.add(paymentTerm);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return paymentTerms;
//        return FindAllResourceFeed.restFeedInitialization("paymentTerm");
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

    public static List<Vendor>  findAllVendors(){

        List<Vendor> vendors = new ArrayList<Vendor>();
        try{
            String allVendors = FindAllResourceFeed.restFeedInitialization("vendor");
            JSONArray jsonArray = new JSONArray(allVendors);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject vendorObject = (JSONObject)jsonArray.get(i);
                Vendor vendor = new Vendor();
                vendor.setId(Integer.parseInt(vendorObject.get("id").toString()));
                vendor.setFirstName(vendorObject.get("firstName").toString());

                vendors.add(vendor);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return vendors;
    }
    
    public static List<PurchaseOrder> findAllPurchaseOrders(){
        List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
        String allPurchaseOrders = FindAllResourceFeed.restFeedInitialization("purchaseOrder");
        try {
            JSONArray jsonArray = new JSONArray(allPurchaseOrders);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject purchaseOrderObject = (JSONObject)jsonArray.get(i);
                PurchaseOrder purchaseOrder = new PurchaseOrder();

                JSONObject vendorJson = (JSONObject)purchaseOrderObject.get("vendor");
                purchaseOrder.setVendor(vendorJson.get("firstName").toString() +" " +vendorJson.get("lastName").toString());

                JSONObject orgaJsonObject = (JSONObject)purchaseOrderObject.get("organization");
                purchaseOrder.setOrganization(orgaJsonObject.get("organizationName").toString());

                purchaseOrders.add(purchaseOrder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return purchaseOrders;
    }

    public static boolean savePurchaseOrder(PurchaseOrder purchaseOrder){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vendorId", purchaseOrder.getVendor());
            jsonObject.put("organizationId", purchaseOrder.getOrganization());
            jsonObject.put("shippingMethodId", purchaseOrder.getShippingMethod());
            jsonObject.put("paymentTermId", purchaseOrder.getPaymentTerm());
            jsonObject.put("deliveryTermId", purchaseOrder.getDeliveryTerm());
            jsonObject.put("shippingAddress", purchaseOrder.getShippingAddress());
            jsonObject.put("orderQuantity", purchaseOrder.getOrderQuantity());
            jsonObject.put("jobName", purchaseOrder.getJonName());
            POSTResourceFeed.post("purchaseOrder", jsonObject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }


    }
}