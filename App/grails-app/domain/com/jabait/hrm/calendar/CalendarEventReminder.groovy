package com.jabait.hrm.calendar

class CalendarEventReminder {
    static belongsTo = [CalendarEvent]

    CalendarEvent event
    String alertType
    int alertEarlyTime = 0
    String timeUnit
    String alerted


    static constraints = {
        timeUnit(nullable:true)
        alerted(nullable:true)
    }
}
