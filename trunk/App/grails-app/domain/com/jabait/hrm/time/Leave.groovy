package com.jabait.hrm.time

class Leave {

    String leaveType;
    Integer leaveDuration;
    String leaveNotes;

    static mapping = {
        table(name: "hr_leave")
    }

    static constraints = {

    }
}
