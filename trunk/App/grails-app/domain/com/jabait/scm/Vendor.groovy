package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.scm.inventory.Product
import com.jabait.security.Address

class Vendor {

    String firstName;
    String lastName;
    Organization organization;
    String address;
    String extendedAddress;
    String country;
    String mobileNo;
    String description;
    String emailId;
    String phoneNo;

    static hasMany = [products : Product]

    static mapping = {
        tablePerHierarchy(false);
    }

    static constraints = {
        firstName(nullable: true);
        lastName(nullable: true);
        organization(nullable: true);
        extendedAddress(nullable: true);
        description(nullable: true);
        mobileNo(nullable: true);
        emailId(nullable: true);
        phoneNo(nullable: true);
    }
}
