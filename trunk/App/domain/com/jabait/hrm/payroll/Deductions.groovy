package com.jabait.hrm.payroll

import com.jabait.hrm.EmployeeProfile
import com.jabait.hrm.Employee

class Deductions {
    Employee payee;
    DeductType deductType;
    Double amount;
    Date deductionDate;
    String note;

    static mapping = {
        deductionDate(sqlType: 'date')
    }
    static constraints = {
    }
}
