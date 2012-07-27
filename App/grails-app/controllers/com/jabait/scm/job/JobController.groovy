package com.jabait.scm.job

class JobController {

    def index() { }

    def createPurchaseOrder(){
        render(view: "");
    }

    def savePurchaseOrder(){
        redirect(action: "createPurchaseOrder")
    }

    def purchaseOrderList(){
        render(view: "");
    }

    def purchaseOrderJsonData(){
        render();
    }

    def createSalesOrder(){
        render(view: "");
    }

    def saveSalesOrder(){
        redirect(action: "createSalesOrder")
    }

    def salesOrderList(){
        render(view: "");
    }

    def salesOrderJsonData(){
        render();
    }
}
