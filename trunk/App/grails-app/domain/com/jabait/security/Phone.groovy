package com.jabait.security


import com.jabait.hrm.EmployeeProfile
import com.jabait.hrm.Organization

class Phone {
    String number;
    String type;

    static belongsTo = [organization:Organization ,employeeProfile: EmployeeProfile]

    static mapping = {
        number type:"text"
    }

    static constraints = {
        type(nullable: true)
        organization(nullable: true)
        employeeProfile(nullable: true)
    }
}
