package com.jabait.hrm

import grails.converters.JSON
import com.jabait.hrm.calendar.CalendarEventSearchCriteria
//import com.jabait.hrm.calendar.CalendarEventSearchCriteria

class CalendarController {

    def calendarSettingService;
    def calendarTypeService;
    def employeeService;
    def calendarEventService;
    def userDetailsService;

    def index(){
        render(view: "calendar_home", model: [type: "Calendar"]);
    }


    def queryUser(){
        params.author = session?.user

        def json = userDetailsService.getListOfUser(params)

        render json as JSON
    }

    def load(){

        def json

        def cs = calendarSettingService.getListOfCalendarSetting(employeeService.getEmployee(session?.user?.id));

        def ct = calendarTypeService.getListOfCalendarType(employeeService.getEmployee(session?.user?.id),
        employeeService.getEmployee(session?.user?.id).employeeProfile.organization);

        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria()
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA']
        }
        eventSC.author = employeeService.getEmployee(session?.user?.id)

        def re = calendarEventService.getListOfRepeatEvents(eventSC)

        json = [cs:cs.results,owned:ct.owned,shared:ct.shared,re:re.results]

        render json as JSON
    }

    def calendarHomeConfig(){
        render(view: "calendar_home", model: [type: "Calendar"])
    }

    def systemCalendar(){
        render(view: "calendar_system", model: [type: "System Calendar"])
    }

    def organizationCalendar(){
        render(view: "calendar_home", model: [type: "Calendar"])
    }

    def employeeCalendar(){
        render(view: "calendar_employee", model: [type: "Employee Calendar"]);
    }
    
    def employeeCalendarView(){
        render(view: "calendar_employee_view", model: [type: "Employee Calendar View"]);
    }
}
