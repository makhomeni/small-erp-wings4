package com.jabait.hrm

import grails.converters.JSON

class CalendarSettingController {

    def calendarSettingService
    def employeeService

    def index() { }

    def list(){
        render calendarSettingService.getListOfCalendarSetting(employeeService.getEmployee(session?.user?.id)) as JSON;
    }

    // update calendar setting ...
    def update(){
        render calendarSettingService.updateCalendarSetting(employeeService.getEmployee(session?.user?.id)) as JSON;
    }
}
