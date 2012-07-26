package com.jabait.hrm.calendar

import com.jabait.hrm.Employee
import com.jabait.hrm.Organization

class CalendarSetting {
    static belongsTo = [Organization]

    Organization organization
    String hourFormat = '24'
    String dayFormat = 'l M d Y'
    String weekFormat = 'm/d(D)'
    String monthFormat = 'm/d'
    String fromtoFormat = 'm/d/Y'
    String language = 'en_US'
    String activeStartTime = '09:00'
    String activeEndTime = '19:00'
    String intervalSlot = '30'
    String startDay = '1'
    boolean createByDblclick = false
    boolean hideInactiveRow = false
    boolean singleDay = false
    boolean readOnly = false
    int initialView = 1

    Date creationDate = new Date()
    Date updateDate = new Date()


    def beforeInsert = {
        creationDate = new Date()
        updateDate = new Date()
    }
    def beforeUpdate = {
        updateDate = new Date()
    }

    static constraints = {
        organization(blank: false)
        hourFormat(blank: false)
        dayFormat(blank: false)
        weekFormat(blank: false)
        monthFormat(blank: false)
        fromtoFormat(blank: false)
        language(blank: false)
        activeStartTime(blank: false)
        activeEndTime(blank: false)
        intervalSlot(blank: false)
    }
    
    public static void initialize(){
        if(CalendarSetting.getCount() == 0){
            CalendarSetting calendarSetting = new CalendarSetting();
            calendarSetting.organization = Organization.get(1);

            calendarSetting.save();
        }

    }
}
