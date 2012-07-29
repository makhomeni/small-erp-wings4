package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod

class PurchaseOrder extends JobOrder {

    Vendor vendor;
    Address shippingAddress;
    ShippingMethod shippingMethod;

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
