package com.jabait.scm.job

class JobController {

    def index() { }

    def createContact(){
        render(view: "/scm/contact_create", model:[type: "Contact Create"]);
    }

    def saveContact(){
        redirect(action: "createContact");
    }

    def contactList(){
        render(view: "contact_list");
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
        render();
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
        render();
    }
}
