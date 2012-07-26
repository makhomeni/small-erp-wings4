package com.jabait.hrm.time

import com.jabait.util.Files

import com.jabait.hrm.EmployeeProfile
import com.jabait.hrm.Employee

class FingerPrintDetails{

    byte[] fingerPrintData;
    Employee employee;

    static mapping = {
        fingerPrintData(sqlType:'blob');
    }

    static constraints = {
    }
}
