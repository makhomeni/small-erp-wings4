package com.jabait.hrm

import com.jabait.security.Name
import com.jabait.security.Address
import com.jabait.security.Email
import com.jabait.security.Identification
import com.jabait.util.Race
import com.jabait.security.Phone


/**
 * Domain Class for basic EmployeeProfile Information
 */
class EmployeeProfile {

    Date employeeBirthDate;
    Date joiningDate;
    Date hireDate;
    String employeeGender;
    String employeeMaritalStatus;
    String employeeStatus;
    Employee owner;
    Boolean providentFundEnable = false;
    Boolean tax = false;

    Department department;
    Organization organization;
    Department section;
    JobTitle jobTitle; //for designation
    String bloodGroup;

    Name name;
    Address employeeAddress;
    Email employeeEmailAddress;
    Race race;
    Identification employeeNationalIdentification;
    Phone homePhone;
    Phone mobile;
    Phone workPhone;
    Email workEmail;
    Email generalEmail;
    Double salary;
    String region;

    static constraints = {

        joiningDate(nullable: true)
        employeeBirthDate(nullable: true)
        hireDate(nullable: true)

        employeeGender(nullable: true)
        employeeMaritalStatus(nullable: true)
        employeeStatus(nullable: true)
        department(nullable: true)
        organization(nullable: true)
        section(nullable: true)
        bloodGroup(nullable: true)
        jobTitle(nullable: true)
        name(nullable: true)
        owner(nullable: true)

        employeeAddress(nullable: true)
        employeeEmailAddress(nullable: true)
        race(nullable: true)
        employeeNationalIdentification(nullable: true)
        homePhone(nullable: true)
        mobile(nullable: true)
        workPhone(nullable: true)
        workEmail(nullable: true)
        generalEmail(nullable: true)
        salary(nullable: true)
        region(nullable: true)
        providentFundEnable(nullable: true)

    }
}
