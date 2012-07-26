package com.jabait.hrm

import com.jabait.hrm.calendar.CalendarEventSearchCriteria

import grails.converters.*
import com.jabait.hrm.calendar.DateUtil
import com.jabait.hrm.calendar.CalendarType

class CalendarEventController {

    def calendarEventService;
    def employeeService;

    def index() { }

    def list(){
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria();
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA']
        }
        eventSC.author = employeeService.getEmployee(session?.user?.id);

        // check to see whether start/end data mentioned...default today
        eventSC.startTime = new Date();
        eventSC.endTime = new Date();

        if (params.startDay) {
            String startDay = params.startDay
            eventSC.startTime = DateUtil.parse(startDay)
        }

        if (params.endDay) {
            String endDay = params.endDay
            eventSC.endTime = DateUtil.parse(endDay)
        }

        session['CALENDAR_EVENT_SEARCH_CRITERIA'] = eventSC

        // get list of services based on condition ...
        render calendarEventService.getListOfEvents(eventSC) as JSON
    }


    def createEditEvent(){
        params.author = employeeService.getEmployee(session?.user?.id);

        if (params.calendarId) {
            CalendarType calendarType = CalendarType.get(params.calendarId);
            params.calendarType = calendarType;
        }


        render calendarEventService.createEditEvent(params) as JSON;
    }


    def deleteEvent(){
        render calendarEventService.deleteEvent(params) as JSON;
    }


    def deleteRepeatEvent(){
        render calendarEventService.deleteRepeatEvent(params) as JSON;
    }


    def deleteByCalendar(){
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria()
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA'];
        }
        eventSC.author = employeeService.getEmployee(session?.user?.id);

        if (params.calendarId) {
            CalendarType calendarType = CalendarType.get(params.calendarId);
            eventSC.calendarType = calendarType;
        }

        render calendarEventService.deleteByCalendar(eventSC) as JSON;
    }


    def deleteDayEvents(){
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria();
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA'];
        }
        eventSC.author = employeeService.getEmployee(session?.user?.id);
        eventSC.startTime = DateUtil.parse(params.day + " 00:00", "yyyy-MM-dd HH:mm");
        eventSC.endTime = DateUtil.parse(params.day + " 23:59", "yyyy-MM-dd HH:mm");

        render calendarEventService.deleteDayEvents(eventSC) as JSON;
    }


    def updateDayEvents(){
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria();
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA'];
        }
        eventSC.author = employeeService.getEmployee(session?.user?.id);
        eventSC.dragDay = params.dragDay;
        eventSC.dropDay = params.dropDay;
        eventSC.startTime = DateUtil.parse(params.dragDay + " 00:00", "yyyy-MM-dd HH:mm");
        eventSC.endTime = DateUtil.parse(params.dragDay + " 23:59", "yyyy-MM-dd HH:mm");

        render calendarEventService.updateDayEvents(eventSC, params.keep) as JSON;
    }


    def search(){
        // get from session ...
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria();
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA'];
        }

        // hard code login user at this
        eventSC.author = employeeService.getEmployee(session?.user?.id);

        if (params.text) {
            eventSC.subject = params.text;
            eventSC.description = params.text;
        }else{
            eventSC.subject = '';
            eventSC.description = '';
        }
        eventSC.start = Integer.valueOf(params.start);
        eventSC.limit = Integer.valueOf(params.limit);
        eventSC.sort = params.sort;
        eventSC.dir = params.dir;

        session['CALENDAR_EVENT_SEARCH_CRITERIA'] = eventSC;

        render calendarEventService.searchListOfEvents(eventSC) as JSON;
    }


    def loadRepeatEvents(){
        CalendarEventSearchCriteria eventSC = new CalendarEventSearchCriteria();
        if (session['CALENDAR_EVENT_SEARCH_CRITERIA']) {
            eventSC = session['CALENDAR_EVENT_SEARCH_CRITERIA'];
        }

        // hard code login user at this
        eventSC.author = employeeService.getEmployee(session?.user?.id);

        session['CALENDAR_EVENT_SEARCH_CRITERIA'] = eventSC;

        render calendarEventService.getListOfRepeatEvents(eventSC) as JSON;
    }


    def createUpdateRepeatEvent(){
        params.author = employeeService.getEmployee(session?.user?.id);

        if (params.calendarId) {
            CalendarType calendarType = CalendarType.get(params.calendarId);
            params.calendarType = calendarType;
        }


        //return json data
        render calendarEventService.createEditRepeatEvent(params) as JSON;
    }


}
