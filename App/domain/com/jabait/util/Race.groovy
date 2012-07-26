package com.jabait.util

import com.jabait.hrm.EmployeeProfile

class Race {
    
    String raceName;
    String description;

    static belongsTo = [employeeProfile: EmployeeProfile]

    static constraints = {
    }
}
