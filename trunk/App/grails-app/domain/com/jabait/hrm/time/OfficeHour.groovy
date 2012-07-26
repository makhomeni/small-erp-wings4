package com.jabait.hrm.time

class OfficeHour {

    Date startTime;
    Date endTime;

    static mapping = {
        startTime(sqlType: "time")
        endTime(sqlType: "time")
    }

    static constraints = {
    }
}
