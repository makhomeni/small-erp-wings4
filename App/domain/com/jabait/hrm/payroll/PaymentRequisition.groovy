package com.jabait.hrm.payroll

import com.jabait.hrm.EmployeeProfile

class PaymentRequisition {
    
    EmployeeProfile incurredBy;
    Date dateIncurred;
    String expenseDescription;
    Double expenseAmount;
    Date dateAccrued;
    Date paid;
    PaymentMethod paymentMethod;

    static constraints = {
    }
}
