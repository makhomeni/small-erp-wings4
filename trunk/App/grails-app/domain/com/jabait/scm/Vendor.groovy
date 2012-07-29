package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.scm.inventory.Product
import com.jabait.security.Address

class Vendor {

    String firstName;
    String lastName;
    Organization organization;
    Address billingAddress;
    String mobileNo;
    String description;
    String emailId;
    String phoneNo;

    static hasMany = [products : Product]

    static mapping = {
        tablePerSubclass(true)
    }

    static constraints = {
        firstName(nullable: true);
        lastName(nullable: true);
        organization(nullable: true);
        billingAddress(nullable: true);
        description(nullable: true);
        mobileNo(nullable: true);
        emailId(nullable: true);
        phoneNo(nullable: true);
    }
}
