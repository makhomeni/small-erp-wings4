package com.jabait.security

import com.jabait.hrm.EmployeeProfile

class Identification {
    
    String nationalIdentificationNumber;

    Passport passport;
    DrivingLicense drivingLicense;


    static belongsTo = [employeeProfile: EmployeeProfile];

    static constraints = {
        passport(nullable: true)
        drivingLicense(nullable: true)
    }
}
