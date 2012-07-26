package com.jabait.hrm.calendar

import com.jabait.hrm.Employee
import com.jabait.hrm.Organization

class CalendarType {


    static belongsTo = [Organization]

    Organization organization
    String name
    String description = null
    String color = 'blue'
    boolean hide = false

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
        name(blank: false)
        description(nullable:true)
    }

    static mapping = {description type:"text" }

    public static void initialize(){
        if(CalendarType.getCount() == 0 ){
            CalendarType calendarType1 = new CalendarType();
            calendarType1.color = "orange";
            calendarType1.description = "leave";
            calendarType1.hide = 0;
            calendarType1.name = "leave";
            calendarType1.organization = Organization.get(1);

            calendarType1.save();

            CalendarType calendarType2 = new CalendarType();
            calendarType2.color = "red";
            calendarType2.description = "absetnt";
            calendarType2.hide = 0;
            calendarType2.name = "absetnt";
            calendarType2.organization = Organization.get(1);

            calendarType2.save();

            CalendarType calendarType3 = new CalendarType();
            calendarType3.color = "blue";
            calendarType3.description = "public holiday";
            calendarType3.hide = 0;
            calendarType3.name = "public holiday";
            calendarType3.organization = Organization.get(1);

            calendarType3.save();

            CalendarType calendarType4 = new CalendarType();
            calendarType4.color = "purple";
            calendarType4.description = "Weekend";
            calendarType4.hide = 0;
            calendarType4.name = "weekend";
            calendarType4.organization = Organization.get(1);

            calendarType4.save();

            CalendarType calendarType5 = new CalendarType();
            calendarType5.color = "green";
            calendarType5.description = "Waiver";
            calendarType5.hide = 0;
            calendarType5.name = "waiver";
            calendarType5.organization = Organization.get(1);

            calendarType5.save();

            CalendarType calendarType6 = new CalendarType();
            calendarType6.color = "sienna";
            calendarType6.description = "Advance LAte Waiver";
            calendarType6.hide = 0;
            calendarType6.name = "advance waiver";
            calendarType6.organization = Organization.get(1);

            calendarType6.save();

        }
    }
}
