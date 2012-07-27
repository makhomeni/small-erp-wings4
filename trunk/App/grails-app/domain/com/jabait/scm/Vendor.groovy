package com.jabait.scm

import com.jabait.hrm.Organization

class Vendor {

    String firstName;
    String lastName;
    Organization organization;
    String address;
    String description;
    String mobileNo;
    String emailId;
    String phoneNo;

    static mapping = {
        tablePerSubclass(true)
    }

    static constraints = {
    }
}
