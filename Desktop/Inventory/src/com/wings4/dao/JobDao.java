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
            JSONObject jsonObject = new JSONObject();
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

    public static List<Sales> findAllSales() {
        List<Sales> saleses = new ArrayList<Sales>();
        return saleses;
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

                JSONObject organizationJsonObject = (JSONObject)purchaseOrderObject.get("organization");
                purchaseOrder.setOrganization(organizationJsonObject.get("organizationName").toString());

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

    public static List<Purchase> findAllPurchases(){
        List<Purchase> purchases = new ArrayList<Purchase>();
        return purchases;
    }
}
