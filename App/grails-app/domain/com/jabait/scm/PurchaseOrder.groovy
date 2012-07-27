package com.jabait.scm

class PurchaseOrder extends JobOrder {

    LocalVendor vendor;

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
