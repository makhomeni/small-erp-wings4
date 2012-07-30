package com.jabait.scm

import com.jabait.hrm.Organization

class Shipment {

    String shipmentName;
    Organization organization;
    Date movementDate;
    String description;

    static mapping = {
        tablePerSubclass(true);
    }

    static constraints = {
    }
}
