package com.jabait.hrm.payroll

import com.jabait.hrm.Employee

class Allowance {

    AllowanceType allowanceType;
    Double allowanceAmount;

    static hasMany = [employees : Employee]
    static belongsTo = [Employee]

    static constraints = {
    }
}
