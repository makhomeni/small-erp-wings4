package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.hrm.payroll.PaymentMethod

class Shipment {

    String shipmentName;
    Organization organization;
    Date movementDate;
    String description;
    PaymentMethod method;

    static mapping = {
        tablePerSubclass(true);
    }

    static constraints = {
    }
}
