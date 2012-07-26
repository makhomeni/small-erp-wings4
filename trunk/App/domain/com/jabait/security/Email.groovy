package com.jabait.security

import com.jabait.hrm.Organization
import com.jabait.hrm.EmployeeProfile

class Email {


    String address;
    String type;

    static belongsTo = [organization:Organization, employeeProfile: EmployeeProfile]

    static constraints = {
        address(email:true)
        type(nullable: true)
        address(size:0..255);
//        employeeProfile(nullable: true)
        organization(nullable: true)
        employeeProfile(nullable: true)
    }
}
