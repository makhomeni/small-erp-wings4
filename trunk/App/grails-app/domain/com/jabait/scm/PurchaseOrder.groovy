package com.jabait.scm

import com.jabait.security.Address
import com.jabait.scm.inventory.ShippingMethod
import com.jabait.scm.inventory.Product
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm
import com.jabait.scm.inventory.InventoryRegister

class PurchaseOrder extends JobOrder {

    Vendor vendor;
    Product product;
    String shippingAddress;
    Organization organization;
    ShippingMethod shippingMethod;
    PaymentTerm paymentTerm;

    static hasMany = [inventoryRegisters : InventoryRegister]

    static mapping = {
        table("purchase_order")
    }
    static constraints = {
    }
}
