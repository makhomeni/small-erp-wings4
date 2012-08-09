package com.jabait.scm

import com.jabait.security.User
import com.jabait.scm.inventory.Product
import com.jabait.scm.inventory.PriceList
import com.jabait.hrm.Organization
import com.jabait.accounting.PaymentTerm
import com.jabait.security.Address

class JobOrder {

    String jobName;
    Integer orderQuantity;
    Date createdDate;
    User createdBy;
    Integer status; //1 draft, 2 Open, 3 Closed
    //Warehouse warehouse; //to be added
    Integer priority; //1 low, 2 medium, 3 high
    Boolean isSent;
    DeliveryTerm deliveryTerm;
    Date dueDate;
    Boolean isArchived;

    static mapping = {
        tablePerSubclass(true);
    }

    static constraints = {
        jobName(unique: true, nullable: false);
        createdDate(nullable: true);
        deliveryTerm(nullable: true);
        dueDate(nullable: true);
    }
}
