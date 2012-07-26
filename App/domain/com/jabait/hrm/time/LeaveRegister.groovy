package com.jabait.hrm.time
import com.jabait.hrm.Employee


class LeaveRegister {
    
    Date fromDate;
    Date toDate;
    Date applyDate = new Date();
    Integer leaveDays;
    String leavePurpose;
    String remark;
    Leave leaveType;
    int leaveStatus = 2;  //0=disapproved, 1=approved, 2=pending
    String approvedDisapprovedReason;


//    static hasMany = [employees : Employee];

    static belongsTo = [employees : Employee]

    static mapping = {

        fromDate(sqlType: 'date')
        toDate(sqlType: 'date')

    }


    static constraints = {
        leaveDays(nullable: true);
        remark(nullable: true);
        leaveType(nullable: true);
        leavePurpose(nullable: true);
        applyDate(nullable: false);
        approvedDisapprovedReason(nullable: true);
    }
}
