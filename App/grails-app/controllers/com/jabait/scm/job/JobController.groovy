package com.jabait.scm.job

import com.jabait.scm.PurchaseOrder
import grails.converters.JSON
import com.jabait.scm.SalesOrder

class JobController {

    def jobService;

    def index() { }

    def createContact(){
        render(view: "/scm/contact_create", model:[type: "Contact Create"]);
    }

    def saveContact(){
        redirect(action: "createContact");
    }

    def contactList(){
        render(view: "contact_list", model:[type: "Contact List"]);
    }

    def createPurchaseOrder(){
        render(view: "/scm/purchase_order_create", model:[type: "Purchase Order Create"]);
    }

    def savePurchaseOrder(){
        redirect(action: "createPurchaseOrder");
    }

    def purchaseOrderList(){
        render(view: "/scm/purchase_order_list", model:[type: "Purchase Order List"]);
    }

    def purchaseOrderJsonData(){
        List purchaseOrders = jobService.findAllPurchaseOrders();
        int max = 10;
        int totalCount = PurchaseOrder.count();

        if (purchaseOrders.size() < 10) {
            max = purchaseOrders.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([purchaseOrders : purchaseOrders.asList().subList(start, start + limit > purchaseOrders.size() ?
            purchaseOrders.size() : start + limit), totalCount : totalCount] as JSON);
    }

    def createSalesOrder(){
        render(view: "/scm/sales_order_create", model:[type: "Sales Order Create"]);
    }

    def saveSalesOrder(){
        redirect(action: "createSalesOrder");
    }

    def salesOrderList(){
        render(view: "/scm/sales_order_list", model:[type: "Sales Order List"]);
    }

    def salesOrderJsonData(){
        List salesOrders = jobService.findAllSalesOrders();
        int max = 10;
        int totalCount = SalesOrder.count();

        if (salesOrders.size() < 10) {
            max = salesOrders.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([purchaseOrders : salesOrders.asList().subList(start, start + limit > salesOrders.size() ?
            salesOrders.size() : start + limit), totalCount : totalCount] as JSON);
    }
}
