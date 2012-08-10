package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product
import com.jabait.accounting.PaymentTerm
import com.jabait.scm.inventory.PriceList
import com.jabait.scm.inventory.InventoryRegister

class SalesOrder extends JobOrder {

    Customer customer;
    Address shippingAddress;
    ShippingMethod shippingMethod;
    PaymentTerm paymentTerm;
    PriceList priceList;

    static hasMany = [inventories : InventoryRegister]

    static mapping = {

        table("sales_order")
    }

    static constraints = {

    }
}
