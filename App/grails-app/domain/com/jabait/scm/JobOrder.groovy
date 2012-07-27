package com.jabait.scm

import com.jabait.security.User
import com.jabait.scm.inventory.Product
import com.jabait.scm.inventory.PriceList
import com.jabait.hrm.Organization

class JobOrder {

    Integer orderQuantity;
    Date createdDate;
    User createdBy;
    Product product;
    Integer status; //1 draft, 2 Open, 3 Closed
    //Warehouse warehouse; //to be added
    Integer priority; //1 low, 2 medium, 3 high
    PriceList priceList;
    Integer paymentTerm; //1 immediate, 2
    Organization organization;

    static mapping = {
        tablePerSubclass(true)
    }

    static constraints = {
    }
}
