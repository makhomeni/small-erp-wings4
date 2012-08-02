package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.hrm.payroll.PaymentMethod
import com.jabait.scm.inventory.ShippingMethod

class Shipment {

    String shipmentName;
    Organization organization;
    Date movementDate;
    String description;
    PaymentMethod method;
    Double shipmentPrice;
    ShippingMethod shippingMethod;
    Date createdOn = new Date();


    static mapping = {
        tablePerSubclass(true);
        createdOn(sqlType: "date")
    }

    static constraints = {
    }
}
