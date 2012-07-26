package com.jabait.ics

import java.sql.Time
import com.jabait.hrm.EmployeeProfile

class CallLog {

    String operationCode; //extension code
    Time startTime;
    Time endTime;
    Date callDate;
    String incomingNumber;
    Customer customer;
    EmployeeProfile operator;

    static hasMany = [confereceCalls : EmployeeProfile]


    static constraints = {
        customer(nullable: true);
        operator(nullable: true);
    }
}
