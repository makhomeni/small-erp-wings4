package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product

class PurchaseOrder extends JobOrder {

    Vendor vendor;
    Address shippingAddress;
    ShippingMethod shippingMethod;

    static hasMany = [products : Product]

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
