package com.jabait.hrm.payroll

import com.jabait.hrm.JobCategory

class SalaryDetails {

    String salaryGradeCode;
    Double minimumSalary;
    Double maximumSalary;
    Double hourlyMinimumWages;
    Double hourlyMaximumWages;
    JobCategory jobCategory;


    static constraints = {
    }
}
