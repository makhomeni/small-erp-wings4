package com.jabait.security

import com.jabait.hrm.Organization
import com.jabait.hrm.EmployeeProfile

class Address {

    String country;
    String extendedAddress;
    String poBox;
    String postalCode;
    String region;
    String streetAddress;

    static belongsTo = [organization: Organization, employeeProfile: EmployeeProfile]

    static constraints = {
        country(nullable:false)
        extendedAddress(nullable:true)
        poBox(nullable: true)
        postalCode(nullable: true)
        region(nullable: true)
        streetAddress(nullable: true)
        organization(nullable: true)
        employeeProfile(nullable: true)
    }
}
