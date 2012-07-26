package com.jabait.hrm.calendar

import com.jabait.hrm.Employee
import com.jabait.hrm.Organization

class CalendarShare {

    static belongsTo = [Organization, CalendarType]

    Organization organization
    CalendarType calendarType = null
    String permit = "all"
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
        calendarType(blank:false)
//        owner(blank:false)
//        user(nullable:true)
        permit(nullable:true)
    }

    public static void initialize(){
        if(CalendarShare.getCount() == 0){

            for (int i = 1; i <= 6; i++){
                CalendarShare calendarShare = new CalendarShare();
                calendarShare.organization = Organization.get(1);
                calendarShare.calendarType = CalendarType.get(i)
                calendarShare.save();
            }

        }

    }

}
