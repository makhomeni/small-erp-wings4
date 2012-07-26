package com.jabait.hrm

import com.jabait.security.Address

class WorkExperience {

    String companyName;
    CompanyBusinessType type;
    Address location;
    String position;
    String department;
    String responsibilities;
    Date startDate;
    Date endDate;

    static hasMany = [employees : Employee]

    static belongsTo = [Employee]


    static constraints = {
    }
}
