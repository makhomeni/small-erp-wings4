package com.jabait.hrm.time

import com.jabait.hrm.Employee
import com.jabait.security.User

class LeaveWaiver {
    
    Employee waiverEmployee;
    Date waiverDate;
    Date waiverTime;
    Date applicationDate = new Date();
    User approvedBy;
    String reason;
    String approvedDisapprovedReason
    int waiverStatus = 2;//0 = disapproved, 1 = approved, 2 = pending
    int waiverType =  0; // 0 = Late, 1 = Advance

    static mapping = {
        waiverTime(sqlType: 'time')
        waiverDate(sqlType: 'date')
    }

    static constraints = {
        waiverEmployee(nullable: false);
        approvedBy(nullable: true);
        approvedDisapprovedReason(nullable: true);
    }
}
