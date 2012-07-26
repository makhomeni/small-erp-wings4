package com.jabait.hrm.calendar

import com.jabait.hrm.Employee

class CalendarEvent {

    static belongsTo = [Employee, CalendarType]

    Employee author
    CalendarType calendarType = null
    String subject
    String description
    String repeatType
    Date startTime = new Date()
    Date endTime = new Date()
    Date creationDate = new Date()
    Date updateDate = new Date()
    boolean locked = false


    def beforeInsert = {
        creationDate = new Date()
        updateDate = new Date()
    }

    def beforeUpdate = {
        updateDate = new Date()
    }


    static constraints = {
        subject(nullable:true)
        description(nullable:true)
    }

    static mapping = {
        description type:"text"
        repeatType type:"text"
    }
}
