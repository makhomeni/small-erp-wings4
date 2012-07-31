package com.jabait.scm.procurement

class ProcurementController {

    def index() { }

    def home(){
        render(view: "/scm/procurement_home", model:[type: "Procurement Home"]);
    }

    def createRequestForQuotation(){
        render(view: "/scm/rfq_create", model:[type: "Request for Quotation Create"]);
    }

    def vendorHome(){
        render(view: "/scm/vendor_home" , model:[type: "Vendor Home"]);
    }
}
