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

    public static List<Product>  findAllProducts(){

        List<Product> products = new ArrayList<Product>();
        try{
            String allProducts = FindAllResourceFeed.restFeedInitialization("product");
            JSONArray jsonArray = new JSONArray(allProducts);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject productObject = (JSONObject)jsonArray.get(i);
                Product product = new Product();
                System.out.println(" product id = "+productObject.get("id").toString());
                product.setProductId(Integer.parseInt(productObject.get("id").toString()));
                product.setProductName(productObject.get("productName").toString());

                products.add(product);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return products;
    }

    public static List<Customer>  findAllCustomers(){

        List<Customer> customers = new ArrayList<Customer>();
        try{
            String allCustomers = FindAllResourceFeed.restFeedInitialization("customer");
            JSONArray jsonArray = new JSONArray(allCustomers);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject customerObject = (JSONObject)jsonArray.get(i);
                Product product = new Product();
                Customer customer = new Customer();
                System.out.println(" product id = "+customerObject.get("id").toString());
                customer.setId(Integer.parseInt(customerObject.get("id").toString()));
                customer.setName(customerObject.get("firstName").toString() + "" + customerObject.get("lastName").toString());
                customers.add(customer);
            }
        }catch(JSONException jE){
            jE.printStackTrace();
        }


        return customers;
    }

    public  static List<SalesOrder> findAllSalesOrder(){
        List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
        try{
            String allSalesOrder = FindAllResourceFeed.restFeedInitialization("salesOrder");
            JSONArray jsonArray = new JSONArray(allSalesOrder);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject salesOrderObject = (JSONObject)jsonArray.get(i);
                SalesOrder salesOrder = new  SalesOrder();
//                salesOrder.setJobName();
            }

        }catch(JSONException jE){
            jE.printStackTrace();
        }

        return salesOrders;
    }

    public static List<LocalVendor>  findAllLocalVendors(){

        List<LocalVendor> localVendors = new ArrayList<LocalVendor>();
        try{
            String allVendors = FindAllResourceFeed.restFeedInitialization("localVendor");
            JSONArray jsonArray = new JSONArray(allVendors);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject vendorObject = (JSONObject)jsonArray.get(i);
                LocalVendor localVendor = new LocalVendor();
                localVendor.setId(Integer.parseInt(vendorObject.get("id").toString()));
                localVendor.setName(vendorObject.get("firstName").toString() + " "+
                        vendorObject.get("lastName").toString());
//                localVendor.setOrganization(vendorObject.get("organization").toString());
                localVendor.setAddress(vendorObject.get("address").toString());
                localVendor.setPhoneNumber(vendorObject.get("phoneNo").toString());
                localVendor.setEmail(vendorObject.get("emailId").toString());

                localVendors.add(localVendor);
            }
        } catch(JSONException jE){
            jE.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }


        return localVendors;
    }

    public static List<ExternalVendor>  findAllExternalVendors(){

        List<ExternalVendor> externalVendors = new ArrayList<ExternalVendor>();
        try{
            String allVendors = FindAllResourceFeed.restFeedInitialization("externalVendor");
            JSONArray jsonArray = new JSONArray(allVendors);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject vendorObject = (JSONObject)jsonArray.get(i);
                ExternalVendor externalVendor = new ExternalVendor();
                externalVendor.setId(Integer.parseInt(vendorObject.get("id").toString()));
                externalVendor.setName(vendorObject.get("firstName").toString() + " "+
                        vendorObject.get("lastName").toString());
                externalVendor.setOrganization(vendorObject.get("organization").toString());
                externalVendor.setAddress(vendorObject.get("address").toString());
                externalVendor.setPhoneNumber(vendorObject.get("phoneNo").toString());
                externalVendor.setEmail(vendorObject.get("emailId").toString());

                externalVendors.add(externalVendor);
            }
        } catch(JSONException jE){
            jE.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }


        return externalVendors;
    }

    public static boolean saveLocalVendor(LocalVendor localVendor){
        try{
            JSONObject jsonObject = new JSONObject(localVendor);
            POSTResourceFeed.post("localVendor", jsonObject);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean saveExternalVendor(ExternalVendor externalVendor){
        try{
            JSONObject jsonObject = new JSONObject(externalVendor);
            POSTResourceFeed.post("externalVendor", jsonObject);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}