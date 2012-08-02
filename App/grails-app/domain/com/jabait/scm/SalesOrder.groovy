package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product

class SalesOrder extends JobOrder {

    Customer customer;
    Address shippingAddress;
    ShippingMethod shippingMethod;

    static hasMany = [products : Product]

    static mapping = {
        table("sales_order")
    }

    static constraints = {
    }
}
