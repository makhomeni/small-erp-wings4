package com.jabait.hrm.payroll

import com.jabait.hrm.Employee

class PayRegister {
    Employee employee;
    Date payDate; //Date salary is paid
    Double salaryDue; //Actual amount of payment
    Date startDay; //From the day the employee would get payment
    Date endDay;   //Up to the day the employee would get payment
    Double grossPay;
    Double deductions;
    Double netPay;
    PaymentMethod paymentMethod; //(Cash, Check, EFT, etc.)
    String paymentIdentifier;    //(Check Number, EFT number, etc.)

    static hasMany = [allowances : Allowance, incentives : Incentives]

    static mapping = {
        payDate(sqlType: 'date');
        startDay(sqlType: 'date');
        endDay(sqlType: 'date');
    }

    static constraints = {
        paymentMethod(nullable: true)
    }
}
