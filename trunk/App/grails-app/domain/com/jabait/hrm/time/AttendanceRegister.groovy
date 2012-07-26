package com.jabait.hrm.time

import com.jabait.hrm.EmployeeProfile
import com.jabait.hrm.Employee

class AttendanceRegister {

    Date jobDate;
    String remark;
    Date inTime;
    Date outTime;
    Double lateMinute;
    Double actualWorkHours;
    Employee attendant;

    static mapping = {
        inTime(sqlType: 'time')
        outTime(sqlType: 'time')
        jobDate(sqlType: 'date')
    }

    static constraints = {
        jobDate(nullable: false);
        inTime(nullable: false);
        outTime(nullable: true);
        actualWorkHours(nullable: true);
        attendant(nullable: false);
    }
}
