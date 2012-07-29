package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod

class SalesOrder extends JobOrder {

    Customer customer;
    Address shippingAddress;
    ShippingMethod shippingMethod;

    static mapping = {
        table("sales_order")
    }

    static constraints = {
    }
}
