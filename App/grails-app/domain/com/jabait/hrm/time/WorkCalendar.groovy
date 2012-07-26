package com.jabait.hrm.time

class WorkCalendar {

    String selectedDays;
    Date startDate;
    Date endDate;
    String appliesTo;

    static hasMany = [workDays : WorkDays]

    static constraints = {
    }
}
