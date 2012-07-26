package com.jabait.hrm.payroll

import com.jabait.hrm.Employee

class LeaveEncashment {

    Employee employee;
    Double amount;
    Date approvedDate;
    Date processedDate;

    static mapping = {
        approvedDate(sqlType: 'date')
        processedDate(sqlType: 'date')
    }

    static constraints = {
        approvedDate(nullable: true)
        processedDate(nullable: true)
    }
}
