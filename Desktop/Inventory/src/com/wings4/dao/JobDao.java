package com.wings4.dao;

import com.wings4.model.Purchase;
import com.wings4.model.PurchaseOrder;
import com.wings4.model.Sales;
import com.wings4.model.SalesOrder;
import com.wings4.util.FindAllResourceFeed;
import com.wings4.util.POSTResourceFeed;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 9/17/12
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobDao {

    public static boolean saveSalesOrder(SalesOrder salesOrder){
        try {
            JSONObject jsonObject = new JSONObject(salesOrder);
//            jsonObject.put("categoryName", salesOrder.getCategoryName());
//            System.out.println("category.getParentCategory() = " + category.getParentCategory());
//            jsonObject.put("parentCategory", category.getParentCategory());
            POSTResourceFeed.post("salesOrder", jsonObject);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static  boolean saveSales(Sales sales){
       try{
           JSONObject jsonObject = new JSONObject(sales);
           POSTResourceFeed.post("sales",jsonObject);
           return true;

       }catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
    }


    public static List<Sales> findAllSales() {
        List<Sales> saleses = new ArrayList<Sales>();
        String allSales = FindAllResourceFeed.restFeedInitialization("sales");
        try {
            JSONArray jsonArray = new JSONArray(allSales);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject salesObject = (JSONObject)jsonArray.get(i);
                Sales sales = new Sales();
                sales.setId(Integer.parseInt(salesObject.get("id").toString()));
                sales.setCustomerName(salesObject.get("customerName").toString());
                sales.setProductName(salesObject.get("productName").toString());
                sales.setSalesDate(salesObject.get("salesDate").toString());
                sales.setQuantity(Integer.parseInt(salesObject.get("quantity").toString()));
                sales.setPrice(Double.parseDouble(salesObject.get("price").toString()));
                saleses.add(sales);
            }
        } catch (JSONException jEx){
            jEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return saleses;
    }
    
    public static List<SalesOrder> findAllSalesOrder(){
        List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();
        String allSalesOrders = FindAllResourceFeed.restFeedInitialization("salesOrder");
        try {
            JSONArray jsonArray = new JSONArray(allSalesOrders);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject salesOrderObject = (JSONObject)jsonArray.get(i);
                SalesOrder salesOrder = new SalesOrder();

                salesOrder.setId(Integer.parseInt(salesOrderObject.get("id").toString()));
                salesOrder.setCustomer(salesOrderObject.get("customer").toString());


                salesOrders.add(salesOrder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return salesOrders;
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
                purchaseOrder.setId(Integer.parseInt(purchaseOrderObject.get("id").toString()));
                purchaseOrder.setVendorId(vendorJson.get("firstName").toString() + " " + vendorJson.get("lastName").toString());

                JSONObject organizationJsonObject = (JSONObject)purchaseOrderObject.get("organization");
                purchaseOrder.setOrganizationId(organizationJsonObject.get("organizationName").toString());

                purchaseOrders.add(purchaseOrder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return purchaseOrders;
    }

    public static boolean savePurchaseOrder(PurchaseOrder purchaseOrder){

        JSONObject jsonObject = new JSONObject(purchaseOrder);
        try {
//            jsonObject.put("vendorId", purchaseOrder.getVendorId());
//            jsonObject.put("organizationId", purchaseOrder.getOrganizationId());
//            jsonObject.put("shippingMethodId", purchaseOrder.getShippingMethodId());
//            jsonObject.put("paymentTermId", purchaseOrder.getPaymentTermId());
//            jsonObject.put("deliveryTermId", purchaseOrder.getDeliveryTermId());
//            jsonObject.put("shippingAddress", purchaseOrder.getShippingAddress());
//            jsonObject.put("orderQuantity", purchaseOrder.getOrderQuantity());
//            jsonObject.put("jobName", purchaseOrder.getJobName());
            POSTResourceFeed.post("purchaseOrder", jsonObject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public static boolean savePurchase(Purchase purchase){

        JSONObject jsonObject = new JSONObject(purchase);
        try {
            POSTResourceFeed.post("purchase", jsonObject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public static List<Purchase> findAllPurchases(){
        List<Purchase> purchases = new ArrayList<Purchase>();
        return purchases;
    }
}
