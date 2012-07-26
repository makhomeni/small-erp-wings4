package com.jabait.hrm.payroll

import com.jabait.hrm.EmployeeProfile
import com.jabait.hrm.Employee

class PayGroup {
    
    String payGroupCode;
    String payGroupDescription;

    static hasMany = [employees : Employee]
    static belongsTo = [Employee]

    static constraints = {
    }
}
