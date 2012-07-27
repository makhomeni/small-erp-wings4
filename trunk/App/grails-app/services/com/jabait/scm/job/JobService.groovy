package com.jabait.scm.job

import com.jabait.scm.PurchaseOrder
import com.jabait.scm.SalesOrder

class JobService {

    def serviceMethod() {

    }

    List findAllPurchaseOrders(){
        List<PurchaseOrder> purchaseOrders = PurchaseOrder.list();
        List<Map<String,Object>> returnedPurchasedOrder = new ArrayList<HashMap<String,Object>>();
        Map<String,Object> purchaseOrderStorage;
        for (PurchaseOrder purchasedOrder : purchaseOrders) {
            purchaseOrderStorage = new HashMap<String,Object>();
            purchaseOrderStorage.put("createdBy", purchasedOrder.createdBy.userCode);
            purchaseOrderStorage.put("vendor", purchasedOrder.vendor.organization.organizationName);
            purchaseOrderStorage.put("createdDate", purchasedOrder.createdDate);
            purchaseOrderStorage.put("status", purchasedOrder.status);
            purchaseOrderStorage.put("paymentTerm", purchasedOrder.paymentTerm);
            purchaseOrderStorage.put("listPrice", purchasedOrder.priceList.listPrice);
            purchaseOrderStorage.put("standardPrice", purchasedOrder.priceList.standardPrice);
            purchaseOrderStorage.put("limitPrice", purchasedOrder.priceList.limitPrice);

            returnedPurchasedOrder.add(purchaseOrderStorage);
        }

        return returnedPurchasedOrder;
    }

    List findAllSalesOrders(){
        List<SalesOrder> salesOrders = SalesOrder.list();
        List<Map<String,Object>> returnedSalesOrder = new ArrayList<HashMap<String,Object>>();
        Map<String,Object> salesOrderStorage;
        for (SalesOrder salesOrder: salesOrders) {
            salesOrderStorage = new HashMap<String,Object>();
            salesOrderStorage.put("createdBy", salesOrder.createdBy.userCode);
            salesOrderStorage.put("customer", salesOrder.customer.organization.organizationName);
            salesOrderStorage.put("createdDate", salesOrder.createdDate);
            salesOrderStorage.put("status", salesOrder.status);
            salesOrderStorage.put("paymentTerm", salesOrder.paymentTerm);
            salesOrderStorage.put("listPrice", salesOrder.priceList.listPrice);
            salesOrderStorage.put("standardPrice", salesOrder.priceList.standardPrice);
            salesOrderStorage.put("limitPrice", salesOrder.priceList.limitPrice);

            returnedSalesOrder.add(salesOrderStorage);
        }

        return returnedSalesOrder;
    }
}
