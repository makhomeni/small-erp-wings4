package com.jabait.hrm

import grails.converters.JSON
import com.jabait.hrm.calendar.CalendarType

class CalendarTypeController {

    def calendarTypeService;
    def employeeService;

    def index() { }

    def list () {
        Employee employee = employeeService.getEmployee(session?.user?.id);

        def json = calendarTypeService.getListOfCalendarType(employee, employee.employeeProfile.organization);

        render json as JSON;
    }

    def showAll(){

        Employee employee = employeeService.getEmployee(session?.user?.id);
        Organization organization = Organization.get(1);
        def json = calendarTypeService.showAllCalendarType(employee);

        render json as JSON;
    }

    def showOnly(){
        //params.author = employeeService.getEmployee(session?.user?.id);
        Organization organization = Organization.get(1);
        def json = calendarTypeService.showOnlyCalendarType();

        render json as JSON;
    }

    def createUpdate(){
        params.author = employeeService.getEmployee(session?.user?.id);

        def json = calendarTypeService.creatUpdateCalendarType(params);

        render json as JSON;
    }

    def delete(){
        params.author = employeeService.getEmployee(session?.user?.id);

        def json = calendarTypeService.deleteCalendarType(params);

        render json as JSON;
    }

    def share(){
        params.author = employeeService.getEmployee(session?.user?.id);

        params.sharedUser = employeeService.getEmployee(params.sharedId);

        params.calendarType = CalendarType.get(params.calendarId);

        def json = calendarTypeService.shareCalendarType(params);

        render json as JSON;
    }


}
