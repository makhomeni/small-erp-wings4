package com.jabait.hrm.time

import com.jabait.hrm.JobCategory

class LeaveEntitlement {
    
    Leave leave;
    JobCategory jobCategory;
    Integer numberOfDays;

    static constraints = {
        leave(nullable: false);
        jobCategory(nullable: false);
        numberOfDays(nullable: false);
    }
}
