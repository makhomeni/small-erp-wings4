package com.jabait.hrm.payroll

import com.jabait.hrm.Employee
import com.jabait.security.User
import com.jabait.accounting.Accounts

class AdvancePayRegister {

    Employee advanceRequester;
    User advanceBy;
    Double advanceAmount;
    Accounts account;
    Date requestedDate;
    Date approvedDate;
    String reasonToApproveReject;
    Integer status; //1 to approve, 2 to reject, 3 to pending

    static mapping = {
        requestedDate(sqlType: 'date');
        approvedDate(sqlType: 'date');
    }

    static constraints = {
        approvedDate(nullable: true);
        reasonToApproveReject(nullable: true);
        account(nullable: true);
    }
}
