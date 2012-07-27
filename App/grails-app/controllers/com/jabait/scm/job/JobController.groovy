package com.jabait.scm.job

class JobController {

    def index() { }

    def createPurchaseOrder(){
        render(view: "/scm/purchase_order_create");
    }

    def savePurchaseOrder(){
        redirect(action: "createPurchaseOrder");
    }

    def purchaseOrderList(){
        render(view: "/scm/purchase_order_list");
    }

    def purchaseOrderJsonData(){
        render();
    }

    def createSalesOrder(){
        render(view: "");
    }

    def saveSalesOrder(){
        redirect(action: "createSalesOrder");
    }

    def salesOrderList(){
        render(view: "/scm/sales_order_list");
    }

    def salesOrderJsonData(){
        render();
    }
}
