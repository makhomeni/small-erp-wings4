package com.jabait.scm

import com.jabait.hrm.payroll.PaymentMethod

class Payment {
    
    Integer transactionId;
    PaymentMethod method;
    Date createdOn;
    Boolean status;
    Double amount;
    JobOrder order;

    static constraints = {
    }
}
