package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.scm.inventory.Product

class Vendor {

    String firstName;
    String lastName;
    Organization organization;
    String address;
    String description;
    String mobileNo;
    String emailId;
    String phoneNo;

    static hasMany = [products : Product]

    static mapping = {
        tablePerSubclass(true)
    }

    static constraints = {
    }
}
