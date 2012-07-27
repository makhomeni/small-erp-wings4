package com.jabait.scm

import com.jabait.security.User
import com.jabait.scm.inventory.Product

class JobOrder {

    Integer orderQuantity;
    Date createdDate;
    User createdBy;
    Product product;

    static mapping = {
        tablePerSubclass(true)
    }

    static constraints = {
    }
}
