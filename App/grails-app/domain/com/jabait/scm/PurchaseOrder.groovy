package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm

class PurchaseOrder extends JobOrder {

    Vendor vendor;
    Address shippingAddress;
    Organization organization;
    ShippingMethod shippingMethod;
    PaymentTerm paymentTerm;

    static hasMany = [products : Product]

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
