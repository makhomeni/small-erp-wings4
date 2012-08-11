package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product
import com.jabait.accounting.PaymentTerm
import com.jabait.scm.inventory.PriceList
import com.jabait.scm.inventory.InventoryRegister

class SalesOrder extends JobOrder {

    Customer customer;
    String address1;
    String address2;
    ShippingMethod shippingMethod;
    PaymentTerm paymentTerm;

    static hasMany = [inventories : InventoryRegister]

    static mapping = {
        table("sales_order")
    }

    static constraints = {
        customer(nullable: true);
        shippingMethod(nullable: true);
        address1(nullable: true);
        address2(nullable: true);
        paymentTerm(nullable: true);
        inventories(nullable: true);
    }
}
