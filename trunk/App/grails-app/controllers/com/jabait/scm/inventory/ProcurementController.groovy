package com.jabait.scm.inventory

class ProcurementController {

    def index() { }

    def home(){
        render(view: "/scm/procurement_home", model:[type: "Procurement Home"]);
    }

    def createRequestForQuotation(){
        render(view: "/scm/rfq_create");
    }

    def vendorHome(){
        render(view: "/scm/vendor_home");
    }
}
