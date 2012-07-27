package com.jabait.scm

class PurchaseOrder extends JobOrder {

    Vendor vendor;

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
