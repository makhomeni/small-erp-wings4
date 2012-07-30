package com.jabait.scm.inventory

class ProcurementController {

    def index() { }

    def createRequestForQuotation(){
        render(view: "/scm/create_rfq");
    }
}
