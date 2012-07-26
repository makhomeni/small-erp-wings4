package com.jabait.security

import com.jabait.hrm.EmployeeProfile

class Name {


    String surname;
    String firstName;
    String middleName;
    String nickname;

    static belongsTo = [employeeProfile: EmployeeProfile];

    static constraints = {
        surname(nullable: true)
        firstName(nullable: false)
        middleName(nullable: true)
        nickname(nullable: true)
        nickname(size:0..255)
    }
}
