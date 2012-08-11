package com.jabait.security

import com.jabait.hrm.Organization
import com.jabait.hrm.EmployeeProfile
import com.jabait.scm.Warehouse

class Address {

    String country;
    String extendedAddress;//Street address 1 and Street address 2 two Text field
    String poBox;
    String postalCode;
    String region;
    String streetAddress;

    static belongsTo = [organization: Organization, employeeProfile: EmployeeProfile,
            wareHouse : Warehouse]

    static constraints = {
        country(nullable:false)
        extendedAddress(nullable:true)
        poBox(nullable: true)
        postalCode(nullable: true)
        region(nullable: true)
        streetAddress(nullable: true)
        organization(nullable: true)
        employeeProfile(nullable: true)
        wareHouse(nullable: true)
    }
}
