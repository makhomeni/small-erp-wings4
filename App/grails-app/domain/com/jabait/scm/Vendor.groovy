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

    public static void initialize(){
        if(Vendor.getCount() == 0){
            Vendor vendor = new Vendor();
            vendor.firstName = "Saleh Enam";
            vendor.lastName = "Shohag";
            vendor.organization = Organization.get(1);
            vendor.address = "318/A";
            vendor.extendedAddress = "318/A";
            vendor.country = "Bangladesh";
            vendor.mobileNo = "0191147868";
            vendor.description = "test vendor";
            vendor.emailId = "shohag@yahoo.com";
            vendor.phoneNo = "02456789";
            vendor.save();
        }
    }
}
