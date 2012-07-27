package com.jabait.scm

import com.jabait.hrm.Organization
import com.jabait.scm.inventory.Product


class LocalVendor extends Vendor {



    String firstName;
    String lastName;
    Organization organization;
    String address;
    String description;
    String mobileNo;
    String emailId;
    String phoneNo;

    static hasMany = [products: Product];

    static constraints = {
        firstName(nullable: true);
        lastName(nullable: true);
        organization(nullable: true);
        address(nullable: true);
        description(nullable: true);
        mobileNo(nullable: true);
        emailId(nullable: true);
        phoneNo(nullable: true);
    }


}
